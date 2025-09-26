package edu.note.java.io.file;

import java.io.InputStream;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/27 下午9:49
 */
public class ResourcesPathTest {

    Class<ResourcesPathTest> clazz = ResourcesPathTest.class;
    ClassLoader classLoader = getClass().getClassLoader();

    @Test
    @DisplayName("类加载器路径")
    void testClassLoaderPath() {
        URL res = classLoader.getResource(".");
        // 测试 Resource 路径
        Assertions.assertNotNull(res); // file:/path/to/test-classes/
        URL root = classLoader.getResource("/");
        System.out.println(root); // null
        Assertions.assertNull(root);
    }

    @Test
    @DisplayName("类路径")
    void testGetResource() {
        URL res = clazz.getResource(".");
        // 测试 Resource 路径
        System.out.println(res);
        Assertions.assertNotNull(res);
        URL res2 = clazz.getResource("/");
        System.out.println(res2);
    }

    @Test
    @DisplayName("获取类加载器路径文件")
    void testGetResourceFile() {
        // 测试 Resource 中是否有 a.txt 文件
        InputStream inputStream = classLoader.getResourceAsStream("a.txt");
        Assertions.assertNotNull(inputStream);
    }
}
