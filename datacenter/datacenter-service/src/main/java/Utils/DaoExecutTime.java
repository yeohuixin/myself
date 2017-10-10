package Utils;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * Created by D7070 on 2017/9/28.
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "select",
        args = {MappedStatement.class, Object.class})})
public class DaoExecutTime implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {


    }
}
