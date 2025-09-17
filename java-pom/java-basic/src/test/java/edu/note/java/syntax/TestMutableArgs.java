package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestMutableArgs {
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

        getSum(10, 20);
        getSum(10, 20, 30);
        getSum(10, 20, 30, 40);

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

    @Test
    @DisplayName("")
    void test03() {
        // 可变参数的小细节：
        // 1.在方法的形参中最多只能写一个可变参数
        // 可变参数，理解为一个大胖子，有多少吃多少

        // 2.在方法的形参当中，如果出了可变参数以外，还有其他的形参，那么可变参数要写在最后

        getSum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    }

    public static int getSum(int a, int... args) {
        return 0;
    }

    @Test
    @DisplayName("")
    void test04() {
        // 计算n个数据的和
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        getSum01(arr);
    }

    public static int getSum01(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum = sum + i;
        }
        return sum;
    }

    @Test
    @DisplayName("")
    void test05() {
        // JDK5
        // 可变参数
        // 方法形参的个数是可以发生变化的，0 1 2 3 ...
        // 格式：属性类型...名字
        // int...args

        int sum = getSum02(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(sum);
    }

    // 底层：
    // 可变参数底层就是一个数组
    // 只不过不需要我们自己创建了，Java会帮我们创建好
    public static int getSum02(int... args) {
        // System.out.println(args);//[I@119d7047
        int sum = 0;
        for (int i : args) {
            sum = sum + i;
        }
        return sum;
    }
}
