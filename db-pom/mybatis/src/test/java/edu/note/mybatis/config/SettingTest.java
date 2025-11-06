package edu.note.mybatis.config;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 配置文件
 *
 * @author jackylee
 * @date 2025/9/28 16:40
 * @desc xml 文件中的设置
 *       https://mybatis.org/mybatis-3/zh_CN/configuration.html
 */
public class SettingTest {

    @Test
    @DisplayName("代码设置")
    void test01() {
        Configuration config = new Configuration();
        config.setCacheEnabled(false);
        assertFalse(config.isCacheEnabled());
    }

    @Test
    @DisplayName("配置文件设置")
    void test02() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-settings.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        Configuration config = factory.getConfiguration();
        assertFalse(config.isCacheEnabled());
    }

}
