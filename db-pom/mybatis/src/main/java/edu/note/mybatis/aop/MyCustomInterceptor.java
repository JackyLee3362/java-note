package edu.note.mybatis.aop;

import java.sql.Statement;
import java.util.Properties;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * @author jackylee
 * @date 2025/9/25 13:20
 */
@Intercepts({@Signature(type = Statement.class, method = "execute", args = {String.class})})
public class MyCustomInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyCustomInterceptor: intercept");
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();

        // 输出 SQL 语句以及参数
        System.out.println("执行的 sql 是 " + sql);
        System.out.println("执行的参数是 " + parameterObject);
        return null;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MyCustomInterceptor: plugin" + target);
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MyCustomInterceptor: setProperties");
        Interceptor.super.setProperties(properties);
    }
}
