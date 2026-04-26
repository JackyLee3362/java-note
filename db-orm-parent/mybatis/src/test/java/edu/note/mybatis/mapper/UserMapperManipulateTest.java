package edu.note.mybatis.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mybatis.model.User;

/**
 * @author jackylee
 * @date 2025-09-29 17:44
 */
public class UserMapperManipulateTest {

    private static SqlSessionFactory factory;
    private SqlSession session;
    private UserMapper userMapper;

    @BeforeAll
    static void setUpClass() throws IOException {
        Reader reader = Resources.getResourceAsReader("mapper/mybatis-config-user-mapper.xml");
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    @BeforeEach
    void setUp() throws SQLException {
        // 配置数据源
        session = factory.openSession();
        userMapper = session.getMapper(UserMapper.class);

        Connection conn = session.getConnection();
        RunScript.execute(conn,
                new StringReader(
                        "DROP TABLE IF EXISTS user;"
                                + "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), city VARCHAR(255));"
                                + "INSERT INTO user (name, city) VALUES ('Foo', 'SH'),('Bar', 'BJ'),('Baz', 'SH');"));
    }

    @AfterEach
    void tearDown() throws SQLException {
        Connection conn = session.getConnection();
        conn.close();
    }

    @Test
    @DisplayName("插入")
    void testInsertUser() {
        User user = new User(null, "Alice", "SH");
        Integer insertSuccess = userMapper.insertUser(user);
        assertEquals(insertSuccess, 1, "插入数据成功");
        assertEquals(4, user.getId(), "插入返回值是 4");
        User queryUser = userMapper.selectById(4);
        assertNotNull(queryUser);
        assertEquals("Alice", queryUser.getName());
    }

    @Test
    @DisplayName("批量插入")
    void testInsertBatch() {
        User u1 = new User(null, "Alice", "SH");
        User u2 = new User(null, "Bob", "GZ");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);

        Integer insertSuccess = userMapper.insertBatch(users);
        assertEquals(insertSuccess, 2, "插入 2 条数据成功");
        assertEquals(4, u1.getId(), "插入返回值是 4");
        assertEquals(5, u2.getId(), "插入返回值是 5");
    }

    @Test
    @DisplayName("参数插入")
    void testInsertUserByParam() {
        Integer insertSuccess = userMapper.insertUserByParam("Alice", "SH");
        assertEquals(insertSuccess, 1, "插入数据成功");
        User queryUser = userMapper.selectById(4);
        assertNotNull(queryUser);
        assertEquals("Alice", queryUser.getName());

    }

    @Test
    @DisplayName("更新")
    void testUpdateUser() {
        User user = new User(1, "Foo Bar", "GZ");
        userMapper.updateUser(user);
        User selectUser = userMapper.selectById(1);
        assertEquals("GZ", selectUser.getCity());

    }

    @Test
    @DisplayName("删除")
    void testDeleteUser() {
        userMapper.deleteUser(2);
        User user = userMapper.selectById(2);
        assertNull(user);
    }
}
