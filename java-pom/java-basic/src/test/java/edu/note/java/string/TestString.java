package edu.note.java.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-13 21:20
 */
public class TestString {

    @Test
    @DisplayName("测试")
    void test_basic_0() {
        // given:
        String a = "hello,world";
        String a2 = "你好世界";
        // when:
        assertEquals(a.length(), 11);
        assertEquals(a2.length(), 4);
        // then:
    }
}
