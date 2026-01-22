package edu.note.java.function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-01-13 15:40
 */
public class FunctionArgumentsTest {

    void method01(String... s) {
        for (String str : s) {
            System.out.println(str);
        }
    }

    @Test
    @DisplayName("测试")
    void test01() {
        String.valueOf((String)null);

    }

}
