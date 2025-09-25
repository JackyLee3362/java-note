package edu.note.mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/25 13:46
 */
public class SqlSessionTest {

    static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setUp() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
    @Test
    void test01(){

    }

}
