package edu.note.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 */
public class EnvironmentsTest {

    @Test
    @DisplayName("环境配置 数据源 - 代码配置")
    void test01() {
        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 配置 MyBatis 环境
        Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
        Configuration config = new Configuration(environment);
    }

    @Test
    @DisplayName("环境配置 - 文件配置")
    void test02() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-env.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

    }
}
