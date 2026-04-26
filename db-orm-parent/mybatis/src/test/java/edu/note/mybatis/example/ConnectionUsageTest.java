package edu.note.mybatis.example;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-09-29 16:26
 * @desc Connection 是 JDBC 的接口
 */
public class ConnectionUsageTest {

    @Test
    @DisplayName("完整 SqlSessionFactory - 代码配置")
    void test01() throws IOException, SQLException {
        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 配置 MyBatis 环境
        Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
        Configuration config = new Configuration(environment);

        // 构建 SqlSessionFactoryTest
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession session = factory.openSession();
        Connection connection = session.getConnection();
        RunScript.execute(connection,
                new StringReader(
                        "DROP TABLE IF EXISTS user; CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY, name VARCHAR(255));"));
        connection.close();
        session.close();
    }

    @Test
    @DisplayName("完整 SqlSessionFactory - 文件配置")
    void test02() throws IOException, SQLException {
        Reader reader = Resources.getResourceAsReader("example/mybatis-config-connection.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = factory.openSession();
        Connection connection = session.getConnection();
        RunScript.execute(connection,
                new StringReader(
                        "DROP TABLE IF EXISTS user;"
                                + "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY, name VARCHAR(255));"
                                + "INSERT INTO user (id, name) VALUES (1, 'Foo');"));
        connection.close();
        session.close();
    }

}
