package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OverloadingTest {
    @Test
    @DisplayName("")
    void test01() {
        getSum(1, 2);
        getSum(1, 2, 3);
        getSum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    // 2 数据和
    public static int getSum(int a, int b) {
        return a + b;
    }

    // 3 数据和
    public static int getSum(int a, int b, int c) {
        return a + b + c;
    }

    // n 数据和 (JDK 5)
    // 可变参数底层是数组
    // 等同于 public static int getSum(int a, int[] args)
    public static int getSum(int a, int... args) {
        int sum = a;
        for (int i : args) {
            sum = sum + i;
        }
        return sum;
    }

}
