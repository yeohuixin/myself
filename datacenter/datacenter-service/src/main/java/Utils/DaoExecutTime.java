package Utils;

import core.dao.DataSourceTypeManager;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * Created by D7070 on 2017/9/28.
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class DaoExecutTime implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
//        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//        BoundSql boundSql = statementHandler.getBoundSql();
//        String sql = boundSql.getSql();
        DataSourceTypeManager.set("");
        System.out.println("mybatis intercept");
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        System.out.println("mybatis intercept dialect:{}" +  dialect);
    }
}
