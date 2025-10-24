package edu.note.java.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FinalTest {

    static int A = 10;
    static int B = Short.MAX_VALUE + 1;

    final int a = 20;
    final int b = Integer.MAX_VALUE;

    @Test
    void test01() {
        final int c = 30;
        new Thread(() -> {
            System.out.println(c);
        }).start();

        final int d = 30;
        class Task implements Runnable {

            @Override
            public void run() {
                System.out.println(d);
            }
        }
        new Thread(new Task()).start();
    }

    @Test
    void test02() {
        assertEquals(10, FinalTest.A);
        assertEquals(32768, FinalTest.B);
        assertEquals(20, new FinalTest().a);
        assertEquals(Integer.MAX_VALUE, new FinalTest().b);
        // new TestFinal().test();
    }

}