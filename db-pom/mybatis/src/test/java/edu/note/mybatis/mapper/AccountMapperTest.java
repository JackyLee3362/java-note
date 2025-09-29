package edu.note.mybatis.mapper;

import edu.note.mybatis.aop.MyCustomInterceptor;
import edu.note.mybatis.model.Account;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.apache.ibatis.session.SqlSession;

/**
 * @author jackylee
 * @date 2025/9/25 13:13
 */
public class AccountMapperTest {

    private AccountMapper accountMapper;

    @BeforeEach
    void setUp() throws IOException, SQLException {
        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 配置 MyBatis 环境
        Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);

        // 注册拦截器
        configuration.addInterceptor(new MyCustomInterceptor());
        // configuration.addInterceptor(new SqlLogInterceptor());

        // 加载 AccountMapper.xml
        Reader mapperReader = Resources.getResourceAsReader("AccountMapper.xml");
        configuration.addMapper(AccountMapper.class);

        // 构建 SqlSessionFactoryTest
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 解析 Mapper 文件
        // 按照文件路径解析，使得访问AccountMapper.xml中的SQL
        new XMLMapperBuilder(mapperReader, configuration, "AccountMapper.xml", configuration.getSqlFragments()).parse();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        accountMapper = sqlSession.getMapper(AccountMapper.class);
        // 初始化数据库 schema & 数据
        Connection connection = dataSource.getConnection();
        RunScript.execute(connection,
                new StringReader(
                        "CREATE TABLE account (id INT PRIMARY KEY, name VARCHAR(255), password VARCHAR(255));"));
        connection.close();

    }

    @Test
    void test01() {
        Account account = new Account(1, "mock account", "mock password");
        accountMapper.insertAccount(account);
        Account account1 = accountMapper.selectById(1);
        Assertions.assertNotNull(account);
        Assertions.assertEquals(account1, account);
    }

}
