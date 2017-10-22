package core.dao.aspect;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * Created by D7070 on 2017/10/17.
 */
public class DataSourceRoutingAspectNew {
    TransactionAttribute transactionAttribute;
    TransactionInterceptor transactionInterceptor;


}
