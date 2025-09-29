package edu.note.thread.basic;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.YieldTest")
public class YieldTest {
    static final int MAX_COUNT = 2000;

    Runnable r1 = () -> {
        int count = 0;
        while (count < MAX_COUNT) {
            log.debug("{}", count++);
        }
    };
    Runnable r2 = () -> {
        int count = 0;
        while (count < MAX_COUNT) {
            Thread.yield();
            log.debug("{}", count++);
        }
    };

    @Test
    void testWithoutYield() throws InterruptedException {
        Thread t1 = new Thread(r1, "🔴");
        Thread t2 = new Thread(r1, "🟡");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    // Junit 在测试并发时，会有些问题，按道理应该等待非守护线程执行完毕
    // 但是 Junit 会在 main 线程执行完毕后调用 System.exit() 退出 JVM
    // 所以有些线程没有执行完，所以我们加上 Thread.join() 方法。
    @Test
    void testWithYield() throws InterruptedException {
        Thread t1 = new Thread(r1, "🔴");
        Thread t2 = new Thread(r2, "🟡");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
