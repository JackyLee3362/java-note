package edu.note.mybatis.aop;


import java.sql.Connection;
import java.util.Properties;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;

/**
 * @author jackylee
 * @date 2025/9/25 19:34
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Connection.class, Integer.class})})
public class SqlLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();

        // 输出 SQL 语句以及参数
        System.out.println("执行的 sql 是 " + sql);
        System.out.println("执行的参数是 " + parameterObject);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
