package edu.note.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/25 13:37
 */
public class SqlSessionFactoryTest {

    @Test
    @DisplayName("使用配置构建 sqlSessionFactory")
    void test01() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        Assertions.assertNotNull(sqlSessionFactory);
    }

    @Test
    @DisplayName("使用代码构建 sqlSessionFactory")
    void test02() {
        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 配置 MyBatis 环境
        Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);

        // 构建 SqlSessionFactoryTest
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        Assertions.assertNotNull(sqlSessionFactory);
    }
}
