package edu.note.java.syntax;

import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 下午2:47
 */
public class TestFinally {

    public static int foo() {
        try {
            System.out.println("try block");
            int a = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch block");
            return 2;
        } finally {
            System.out.println("finally block");
        }
        return 0;
    }

    public static int foo1() {
        int a = 2;
        try {
            System.out.println("try block");
            return a += 10;

        } catch (Exception e) {
            System.out.println("catch block");
            return 2;
        } finally {
            System.out.println("finally block");
            System.out.println("a = " + a);
            return a + 100;
        }
    }

    @Test
    public void test01() {
        System.out.println(foo());
        System.out.println(foo1());
        // 结论，不管多少return，finally块都会执行
    }

}
