package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestHashCode {

    @Test
    @DisplayName("哈希碰撞")
    void test02() {

        // 哈希碰撞
        System.out.println("abc".hashCode());// 96354
        System.out.println("acD".hashCode());// 96354

    }
}
