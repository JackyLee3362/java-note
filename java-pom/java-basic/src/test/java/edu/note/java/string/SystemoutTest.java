package edu.note.java.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-05-17 09:19
 */
public class SystemoutTest {

    @Test
    @DisplayName("测试")
    void test_1() {
        System.out.println("打印正常");
        System.err.println("打印异常");
    }
}
