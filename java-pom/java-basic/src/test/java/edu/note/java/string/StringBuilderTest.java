package edu.note.java.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-12-25 09:56
 */
public class StringBuilderTest {

    @Test
    @DisplayName("测试")
    void test01() {
        // given:
        StringBuilder sb = new StringBuilder();
        // and:
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        // when:
        String result = sb.toString();
        // then:
        assertEquals("Hello World", result);

        // given:
        sb.setLength(0);
        assertEquals("", sb.toString());

    }
}
