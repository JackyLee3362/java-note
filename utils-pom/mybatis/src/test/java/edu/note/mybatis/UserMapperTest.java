package edu.note.mybatis;

import edu.note.mybatis.mapper.UserMapper;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import org.apache.ibatis.session.SqlSession;

/**
 * @author jackylee
 * @date 2025/9/25 11:58
 */
public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    public void setUp() throws IOException, SQLException {
        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 配置 MyBatis 环境
        Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);

        // 加载 UserMapper.xml
        Reader mapperReader = Resources.getResourceAsReader("UserMapper.xml");
        configuration.addMapper(UserMapper.class);

        // 构建 SqlSessionFactoryTest
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 解析 Mapper 文件
        // 按照文件路径解析，使得访问UserMapper.xml中的SQL
        new XMLMapperBuilder(mapperReader, configuration, "UserMapper.xml", configuration.getSqlFragments()).parse();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        // 初始化数据库 schema & 数据
        Connection connection = dataSource.getConnection();
        RunScript.execute(connection,
            new StringReader("CREATE TABLE user (id INT PRIMARY KEY, name VARCHAR(255));" +
                             "INSERT INTO user (id, name) VALUES (1, 'Test User');"));
        connection.close();

        // // 初始化数据库，插入测试数据
        // sqlSession.update("CREATE TABLE User (id INT PRIMARY KEY, name VARCHAR(255))");
        // sqlSession.update("INSERT INTO User (id, name) VALUES (1, 'Test User')");
        // sqlSession.commit();
    }


    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Test User", user.getName());
    }

    @Test
    public void testInsertUser() {
        User user = new User(2, "Test Bob", 12);
        userMapper.insertUser(user);
        User queryUser = userMapper.selectById(2);
        Assertions.assertNotNull(queryUser);
        Assertions.assertEquals("Test Bob", queryUser.getName());
    }
}
