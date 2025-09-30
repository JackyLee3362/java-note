package edu.note.java.syntax;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/11 14:32
 */
public class VolatileTest {

    // 如果没有 volatile ，线程不会停止
    static boolean run = true;

    @Test
    @DisplayName("测试 violate")
    void test0() throws InterruptedException {

        System.out.println("caller: 开始");
        Thread t1 = new Thread(() -> {
            System.out.println("t1: 开始运行");
            while (run) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("caller: 停止");
        run = false;
        t1.join();
    }

}
