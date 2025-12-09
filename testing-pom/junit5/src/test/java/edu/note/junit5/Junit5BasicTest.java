package edu.note.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-12-09 19:40
 */
public class Junit5BasicTest {
    @Test
    @DisplayName("断言测试")
    void test01() {
        assertEquals(5, 2 + 3);
        assertTrue(true);
    }

    @Test
    @Disabled
    @DisplayName("跳过测试")
    void test02() {
        assertEquals(5, 2 + 3);
        assertTrue(true);
    }
}
