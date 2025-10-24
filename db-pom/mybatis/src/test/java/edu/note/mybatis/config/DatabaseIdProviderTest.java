package edu.note.mybatis.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/28 16:40
 */
public class DatabaseIdProviderTest {

    @Test
    @DisplayName("数据库 id 自定义 provider - 代码配置")
    void test01() throws IOException, SQLException {
        DatabaseIdProvider databaseIdProvider = new ExampleDatabaseIdProvider();
        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        String databaseId = databaseIdProvider.getDatabaseId(dataSource);
        assertEquals("H2", databaseId);
    }

    @Test
    @DisplayName("数据库 id 默认 provider - 文件配置")
    void test02() throws IOException, SQLException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-database-id.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        try (SqlSession session = factory.openSession()) {
            Connection connection = session.getConnection();
            String databaseId = connection.getMetaData().getDatabaseProductName();
            // 获取版本
            String databaseProductVersion = connection.getMetaData().getDatabaseProductVersion();
            assertEquals("H2", databaseId);
            assertNotNull(databaseProductVersion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
