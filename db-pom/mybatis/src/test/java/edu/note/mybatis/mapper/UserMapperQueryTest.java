package edu.note.mybatis.mapper;

import edu.note.mybatis.model.User;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.apache.ibatis.session.SqlSession;

/**
 * @author jackylee
 * @date 2025/9/25 11:58
 */
public class UserMapperQueryTest {

    private static SqlSession session;
    private static UserMapper userMapper;

    @BeforeAll
    static void setUp() throws IOException, SQLException {
        // 配置数据源
        Reader reader = Resources.getResourceAsReader("mapper/mybatis-config-user-mapper.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        session = factory.openSession();
        userMapper = session.getMapper(UserMapper.class);

        Connection conn = session.getConnection();
        RunScript.execute(conn,
                new StringReader(
                        "DROP TABLE IF EXISTS user;"
                                + "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), city VARCHAR(255));"
                                + "INSERT INTO user (name, city) VALUES ('Foo', 'SH'),('Bar', 'BJ'),('Baz', 'SH');"));
    }

    @Test
    @DisplayName("根据 id 查询")
    void testSelectById() {
        User user = userMapper.selectById(1);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Foo", user.getName());
    }

    @Test
    @DisplayName("根据 name 查询")
    void testSelectByName() {
        User user = userMapper.selectByName("Foo");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Foo", user.getName());
    }

    @Test
    @DisplayName("根据 List 查询")
    void testList() {
        List<User> users = userMapper.list();
        Assertions.assertNotNull(users);
        Assertions.assertEquals("Foo", users.get(0).getName());
        Assertions.assertEquals("Bar", users.get(1).getName());
    }

    @Test
    @DisplayName("根据 listAnno 查询")
    void testListByAnno() {
        List<User> users = userMapper.listByAnno();
        Assertions.assertNotNull(users);
        Assertions.assertEquals("Foo", users.get(0).getName());
        Assertions.assertEquals("Bar", users.get(1).getName());
    }

}
