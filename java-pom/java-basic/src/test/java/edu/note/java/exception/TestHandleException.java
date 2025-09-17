package edu.note.java.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHandleException {

    @Test
    void test01() {

        /*
         * getMessage() 返回此 throwable 的详细消息字符串
         * toString() 返回此可抛出的简短描述
         *
         * printStackTrace() 在底层是利用System.err.println进行输出
         * 把异常的错误信息以红色字体输出在控制台
         * 仅仅是打印信息，不会停止程序运行
         */

        int[] arr = { 1, 2, 3, 4, 5, 6 };

        try {
            arr[10] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {

            // Index 10 out of bounds for length 6
            Assertions.assertEquals("Index 10 out of bounds for length 6", e.getMessage());

            // java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length
            Assertions.assertEquals("java.lang.ArrayIndexOutOfBoundsException: 10", e.toString());

            e.printStackTrace();

        }
        System.out.println("看看我执行了吗？");

        // 正常输出语句
        System.out.println(123);

        // 错误输出语句（而是用来打印错误信息）
        System.err.println(123);

    }
}
