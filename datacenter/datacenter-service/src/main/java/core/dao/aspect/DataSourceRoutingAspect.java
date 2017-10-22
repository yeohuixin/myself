package core.dao.aspect;

import core.dao.DataSourceTypeManager;
import core.dao.ThreadLocalRoutingDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by D7070 on 2017/10/17.
 */
@Aspect
@Order(0)
public class DataSourceRoutingAspect implements BeanPostProcessor{
    private Map<String, Boolean> readMethodMap = new HashMap<String, Boolean>();

    private List<String> lookupMasterKeys = new ArrayList<String>();

    private List<String> lookupSlaveKeys = new ArrayList<String>();

    private Boolean forceChoiceReadWhenWrite = Boolean.TRUE;

    public void setForceChoiceReadWhenWrite(boolean forceChoiceReadWhenWrite) {
        this.forceChoiceReadWhenWrite = forceChoiceReadWhenWrite;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof NameMatchTransactionAttributeSource) {
            try {
                NameMatchTransactionAttributeSource transactionAttributeSource = (NameMatchTransactionAttributeSource) bean;
                Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
                nameMapField.setAccessible(true);
                Map<String, TransactionAttribute> nameMap = (Map<String, TransactionAttribute>) nameMapField.get(transactionAttributeSource);

                for (Map.Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
                    RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute) entry.getValue();

                    //仅对read-only的处理
                    if (!attr.isReadOnly()) {
                        continue;
                    }

                    String methodName = entry.getKey();
                    Boolean isForceChoiceRead = Boolean.FALSE;
                    if (forceChoiceReadWhenWrite) {
                        //不管之前操作是写，默认强制从读库读 （设置为NOT_SUPPORTED即可）
                        //NOT_SUPPORTED会挂起之前的事务
                        attr.setPropagationBehavior(Propagation.NOT_SUPPORTED.value());
                        isForceChoiceRead = Boolean.TRUE;
                    } else {
                        //否则 设置为SUPPORTS（这样可以参与到写事务）
                        attr.setPropagationBehavior(Propagation.SUPPORTS.value());
                    }
                    readMethodMap.put(methodName, isForceChoiceRead);
                }
            } catch (Exception e) {
                System.out.println("Init readMethodMap occur exception : " + e);
            }
        }

        if (bean instanceof ThreadLocalRoutingDataSource) {
            try {
                ThreadLocalRoutingDataSource routingDataSource = (ThreadLocalRoutingDataSource) bean;
                Field targetDataSourcesField = ReflectionUtils.findField(ThreadLocalRoutingDataSource.class, "targetDataSources");
                targetDataSourcesField.setAccessible(true);
                Map<Object, Object> targetDataSources = (Map<Object, Object>) targetDataSourcesField.get(routingDataSource);

                for (Map.Entry<Object, Object> entry : targetDataSources.entrySet()) {
                    if (entry.getKey().toString().contains(DataSourceTypeManager.MASTER)) {
                        lookupMasterKeys.add(entry.getKey().toString());
                    } else if (entry.getKey().toString().contains(DataSourceTypeManager.SLAVE)) {
                        lookupSlaveKeys.add(entry.getKey().toString());
                    }
                }
            } catch (Exception e) {
                System.out.println("Init lookupMasterKeys or lookupSlaveKeys occur exception : " + e);
            }
        }
        return bean;
    }

    @Pointcut("execution(public * service.*.*(..))")
    public void aspectjMethod() {
        System.out.println("aspectj method start!");
    }

    @Around(value = "aspectjMethod()")
    public Object aroundAdviceO(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        if (isChoiceReadDB(pjp.getSignature().getName())) {
            Collections.shuffle(lookupSlaveKeys);
            DataSourceTypeManager.markRead(lookupSlaveKeys.get(0));
        } else {
            Collections.shuffle(lookupMasterKeys);
            DataSourceTypeManager.markWrite(lookupMasterKeys.get(0));
        }
        try {
            return pjp.proceed();
        } finally {
            DataSourceTypeManager.reset();
        }
    }

    private boolean isChoiceReadDB(String methodName) {
        String bestNameMatch = null;
        for (String mappedName : this.readMethodMap.keySet()) {
            if (PatternMatchUtils.simpleMatch(mappedName, methodName)) {
                bestNameMatch = mappedName;
                break;
            }
        }

        Boolean isForceChoiceRead = readMethodMap.get(bestNameMatch);
        //表示强制选择读库
        if (isForceChoiceRead == Boolean.TRUE) {
            return true;
        }

        //如果之前选择了写库 现在还选择 写库
        if (DataSourceTypeManager.isChoiceWrite()) {
            return false;
        }

        //表示应该选择读库
        if (isForceChoiceRead != null) {
            return true;
        }

        //默认选择 写库
        return false;
    }
}
