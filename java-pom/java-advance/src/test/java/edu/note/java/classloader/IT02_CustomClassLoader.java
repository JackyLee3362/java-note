package edu.note.java.classloader;

import edu.note.java.classLoader.CustomClassLoader;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/28 下午11:10
 */
@Slf4j
public class IT02_CustomClassLoader {


    ClassLoader classLoader = IT02_CustomClassLoader.class.getClassLoader();
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

