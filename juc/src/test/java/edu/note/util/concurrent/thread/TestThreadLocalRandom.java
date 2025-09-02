package edu.note.util.concurrent.thread;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestThreadLocalRandom {
    @Test
    @DisplayName("测试随机数")
    void test() {
        ThreadLocalRandom.current().nextInt();
    }
}
