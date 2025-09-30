package edu.note.java.syntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 下午2:47
 */
public class TryCatchFinallyTest {

    public static Integer catchOuterReturn(Integer divider) {
        Integer a = -1;
        try {
            System.out.println("try block");
            a = 1 / divider;
        } catch (Exception e) {
            System.out.println("catch block");
            return Integer.MIN_VALUE;
        } finally {
            System.out.println("finally block");
            System.out.println("a = " + a);
        }
        return a;
    }

    public static Integer tryCatchFinalReturn(Integer divider) {
        Integer a = -1;
        try {
            System.out.println("try block");
            a = 1 / divider;
            return a;
        } catch (Exception e) {
            System.out.println("catch block");
            return Integer.MIN_VALUE;
        } finally {
            System.out.println("finally block");
            System.out.println("a = " + a);
            return Integer.MAX_VALUE;
        }
    }

    public static Integer catchFinalReturn(Integer divider) {
        Integer a = -1;
        try {
            System.out.println("try block");
            a = 1 / divider;
        } catch (Exception e) {
            System.out.println("catch block");
            return Integer.MIN_VALUE;
        } finally {
            System.out.println("finally block");
            System.out.println("a = " + a);
            return Integer.MAX_VALUE;
        }
    }

    @Test
    void test01() {
        Assertions.assertEquals(Integer.MIN_VALUE, catchOuterReturn(0));
        Assertions.assertEquals(Integer.MAX_VALUE, catchFinalReturn(0));
        Assertions.assertEquals(Integer.MAX_VALUE, tryCatchFinalReturn(1));
        // 结论，不管多少return，finally块都会执行
    }

}
