package edu.note.java.exception;

import org.junit.jupiter.api.Test;

public class TestCatchMultiException {

    @Test
    void test() {

        /*
         * 在JDK7之后，我们可以在catch中同时捕获多个异常，中间用|进行隔开
         * 表示如果出现了A异常或者B异常的话，采取同一种处理方案
         */

        int[] arr = { 1, 2, 3, 4, 5, 6 };

        try {
            System.out.println(arr[10]);// ArrayIndexOutOfBoundsException
            System.out.println(2 / 0);// ArithmeticException
            String str = null;
            System.out.println(str.equals("abc"));
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println("索引越界了");
        } catch (NullPointerException e) {
            System.out.println("空指针异常");
            // 多个异常，父类在下
        } catch (Exception e) {
            System.out.println("Exception");
        }

        System.out.println("结束");

    }
}
