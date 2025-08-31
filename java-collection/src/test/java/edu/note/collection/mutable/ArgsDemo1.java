package edu.note.collection.mutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArgsDemo1 {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * 假如需要定义一个方法求和，该方法可以灵活的完成如下需求：
         * 计算2个数据的和
         * 计算3个数据的和
         * 计算4个数据的和
         * 计算n个数据的和
         */

        System.out.println(getSum(10, 20));
        System.out.println(getSum(10, 20, 30));
        System.out.println(getSum(10, 20, 30, 40));

    }

    // 计算2个数据的和
    public static int getSum(int a, int b) {
        return a + b;
    }

    // 计算3个数据的和
    public static int getSum(int a, int b, int c) {
        return a + b + c;
    }

    // 计算4个数据的和
    public static int getSum(int a, int b, int c, int d) {
        return a + b + c + d;
    }

}
