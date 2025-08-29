package edu.note.java.io;

import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/28 上午11:35
 */
public class BaseIOTest {

    public static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    public static final String resource = Objects.requireNonNull(classLoader.getResource(".")).getPath();

    @Test
    void testBasic() {
        Assertions.assertInstanceOf(String.class, resource);
    }

}
