package edu.note.mybatis.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/26 14:04
 */
public class PropertyTest {

    @Test
    @DisplayName("使用配置+注入属性")
    void test03() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
        Reader propReader = Resources.getResourceAsReader("config/db.property");
        // 加载配置
        Properties prop = new Properties();
        prop.load(propReader);
        // 构建工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, prop);
        // 获取配置
        Configuration config = factory.getConfiguration();
        assertEquals("org.h2.Driver", config.getVariables().getProperty("driver"));
    }

    @Test
    @DisplayName("使用配置(内联属性文件路径)")
    void test04() throws IOException {
        Reader reader = Resources.getResourceAsReader("config/mybatis-config-prop.xml");
        // 构建工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        // 获取配置
        Configuration config = factory.getConfiguration();
        assertEquals("org.h2.Driver", config.getVariables().getProperty("driver"));
    }

    @Test
    @DisplayName("属性优先级")
    void test05() {
        // 读取顺序(优先级递增)
        // 1. 读取 properties 元素体内指定的属性
        // 2. 读取 properties 元素中的 resource/url 属性读取类路径下属性文件，覆盖之前的同名属性
        // 3. 读取作为方法参数传递的属性，并覆盖之前的同名属性
    }

    @Test
    @DisplayName("默认属性")
    void test06() {
        // ${username:ut_user}: 如果属性 'username' 没有被配置，'username' 属性的值将为 'ut_user'
        // 如果属性名中使用了 ":" 这样的值，比如 "db:username"，则需要修改属性分隔符
    }

}
