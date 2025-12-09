package edu.note.junit5;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-21 23:05
 */
public class Junit5ExceptionTest {
    @Test
    @DisplayName("测试捕获异常")
    void test01() {
        assertThrows(Exception.class, ()->{
            throw new Exception();
        });
    }

}
