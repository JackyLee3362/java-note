package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-12-25 09:44
 */
public class FunctionArgumentTest {

    void func1(int a, String b) {
        a = 100;
        b = "changed";
    }

    @Test
    @DisplayName("测试 函数参数传递")
    void test01() {

        int num = 10;
        String str = "original";

        func1(num, str);

        assert num == 10;
        assert str.equals("original");

    }
}
