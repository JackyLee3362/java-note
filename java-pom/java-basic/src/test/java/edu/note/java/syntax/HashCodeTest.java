package edu.note.java.syntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashCodeTest {

    @Test
    @DisplayName("哈希碰撞")
    void test01() {

        // 哈希碰撞
        Assertions.assertEquals(96354, "abc".hashCode());
        Assertions.assertEquals(96354, "acD".hashCode());

    }
}
