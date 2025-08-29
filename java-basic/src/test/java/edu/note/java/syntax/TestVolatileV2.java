package edu.note.java.syntax;

import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/11 14:32
 */
public class TestVolatileV2 {

    // 如果没有 volatile ，线程不会停止
    volatile static boolean run = true;

    @Test
    void test01() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("开始运行");
            while (run) {
                // ...
                System.out.println();
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("停止");
        run = false;
        t1.join();
    }

}
