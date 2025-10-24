package edu.note.mybatis.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 */
public class PluginTest {

    /**
     * 
     * MyBatis 允许你在映射语句执行过程中的某一点进行拦截调用。
     * 默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
     * Executor (update, query, flushStatements, commit, rollback,
     * getTransaction,close, isClosed)
     * ParameterHandler (getParameterObject, setParameters)
     * ResultSetHandler (handleResultSets, handleOutputParameters)
     * StatementHandler (prepare, parameterize, batch, update, query)
     */
    @Test
    @DisplayName("测试插件")
    void test01() {
        // 对象工厂处理
        Configuration config = new Configuration();
        Interceptor interceptor = new ExamplePlugin();
        config.addInterceptor(interceptor);
        assertTrue(config.getInterceptors().stream().allMatch(o -> o instanceof ExamplePlugin));
    }
}
