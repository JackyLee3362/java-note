package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 内部类的静态成员测试
 */
class TestInnerClass {

    static class Engine {

        public static int version;
    }

    @Test
    @DisplayName("内部类")
    void test() {
        int version = Engine.version;
        System.out.println(version);
    }
}