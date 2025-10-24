package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashCodeTest {

    @Test
    @DisplayName("哈希碰撞")
    void test01() {

        // 哈希碰撞
        assertEquals(96354, "abc".hashCode());
        assertEquals(96354, "acD".hashCode());

    }
}
