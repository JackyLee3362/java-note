package edu.note.java.syntax;

import org.junit.jupiter.api.Test;

public class TestFinal {

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
        System.out.println(TestFinal.A);
        System.out.println(TestFinal.B);
        System.out.println(new TestFinal().a);
        System.out.println(new TestFinal().b);
        // new TestFinal().test();
    }

    @Test
    void test03() {
        System.out.println(TestFinal.A);
    }
}