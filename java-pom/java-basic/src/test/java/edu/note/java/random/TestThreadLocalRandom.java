package edu.note.java.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestThreadLocalRandom {
    @Test
    @DisplayName("测试随机数")
    void test() {
        // ThreadLocalRandom.current().nextInt(0, 1);
        Boolean b = null;
        if (b) {
            System.out.println("hello");
        } else {
            System.out.println("Fail");
        }
    }

}
