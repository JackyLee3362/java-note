package edu.note.java.loader;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import edu.note.java.loader.CustomClassLoader;

/**
 * @author jackylee
 * @date 2024/11/28 下午11:10
 */
@Slf4j
public class CustomClassLoaderTest {


    ClassLoader classLoader = CustomClassLoaderTest.class.getClassLoader();
    String url = Objects.requireNonNull(classLoader.getResource(".")).getPath();
    CustomClassLoader customClassLoader = new CustomClassLoader(url);

    @Test
    void test1() {
        try {
            Class<?> clazz = customClassLoader.loadClass("Person");
            clazz.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void test2() {
        try {
            Class<?> clazz2 = customClassLoader.loadClass("com.jackylee.classLoader.Person2");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

