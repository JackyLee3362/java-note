package edu.note.mybatis.example;

import edu.note.mybatis.mapper.UserMapper;
import edu.note.mybatis.model.User;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/25 13:46
 */
public class MappersUsageTest {

    static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setUp() throws IOException {
        Reader reader = Resources.getResourceAsReader("example/mybatis-config-mapper.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Connection connection = session.getConnection();
            RunScript.execute(connection,
                    new StringReader(
                            "DROP TABLE IF EXISTS user;"
                                    + "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY, name VARCHAR(255));"
                                    + "INSERT INTO user (id, name) VALUES (1, 'Test User');"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("原始调用")
    void test01() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User o = (User) session.selectOne("edu.note.mybatis.mapper.UserMapper.selectById", 1);
            System.out.println(o);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("高级调用")
    void test02() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectById(1);
            System.out.println(user);
            User user2 = mapper.selectByName("Test User");
            System.out.println(user2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
