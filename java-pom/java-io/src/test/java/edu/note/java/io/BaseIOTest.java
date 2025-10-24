package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.File;
import java.util.Objects;

import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/28 上午11:35
 */
public class BaseIOTest {

    public static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    public static final String resource = Objects.requireNonNull(classLoader.getResource(".")).getPath();
    public static final File ioDir = new File(resource, "io");
    public static final File fileDir = new File(resource, "file");

    @Test
    void testBasic() {
        assertInstanceOf(String.class, resource);
    }

}
