package edu.note.collection.map;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/26 08:44
 */
public class PropertiesTest {

    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    @DisplayName("测试 Properties")
    void test01() {
        Properties prop = new Properties();
        prop.setProperty("name", "alice");
        prop.setProperty("age", "18");
        Assertions.assertEquals("alice", prop.getProperty("name"));
    }

    @Test
    @DisplayName("测试从文件加载 Properties")
    void test02() {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.property")) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("root", prop.getProperty("database.username"));
    }

}
