package edu.note.mybatis.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * sqlSessionFactory 配置
 *
 * @author jackylee
 * @date 2025/9/25 13:37
 */
public class SqlSessionFactoryTest {

    @Test
    @DisplayName("使用代码构建")
    void test01() {
        Configuration configuration = new Configuration();
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        assertNotNull(sqlSessionFactory);
    }

    @Test
    @DisplayName("使用配置文件构建")
    void test02() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        assertNotNull(sqlSessionFactory);
    }

}
