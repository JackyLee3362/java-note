package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-01-04 19:40
 */
public class CompareToTest {
    @Test
    @DisplayName("测试")
    void test01() {
        Integer a = 1;
        Integer b = 3;
        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));
        assertEquals(0, a.compareTo(a));

    }
}
