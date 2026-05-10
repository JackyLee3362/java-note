package edu.note.java.juc.thread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptTest {

    @Test
    @DisplayName("测试打断标记测试")
    void test_is_interrupted() {
        log.info("当前标记为: {}", Thread.currentThread().isInterrupted());

        log.info("打断自身");
        Thread.currentThread().interrupt();
        log.info("打断自身后, 打断标记不会清除, 当前打断标记为: {}(预期为 true)", Thread.currentThread().isInterrupted());

        log.info("静态方法打断");
        Thread.interrupted();
        log.info("静态方法打断后, 打断标记会清除, 当前标记为: {}(预期为 false)", Thread.currentThread().isInterrupted());

    }

    // @RepeatedTest(10)
    // 打断 sleep 中的线程，抛出 InterruptedException，重置打断标记为 false
    @Test
    @DisplayName("打断 sleep 线程, 打断标记重置")
    void test_interrupt_sleep() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("开始, 当前打断标记为: {}", Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.info("sleep 被打断, 当前打断标记为: {}", Thread.currentThread().isInterrupted());
            }
            log.info("结束");
        }, "thr1");

        t1.start();
        Sleeper.sleep(0.2);

        log.info("主线程打断 t1 线程");
        t1.interrupt();

        Sleeper.sleep(0.2);
        log.info("t1线程状态:{}(预期为 TERMINATED), 是否存活: {}", t1.getState(), t1.isAlive());

        t1.join();
    }

    // @RepeatedTest(10)
    // 打断正常运行的线程，打断标记是 true
    @Test
    @DisplayName("打断正常运行的线程, 打断标记不重置")
    void test03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("开始, 当前打断标记为: {}", Thread.currentThread().isInterrupted());
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("正常运行被打断, 打断标记为为 true");
                    break;
                }
            }
        }, "thr1");
        t1.start();
        Sleeper.sleep(0.2);

        log.info("主线程打断 t1 线程");
        t1.interrupt();

        Sleeper.sleep(0.2);
        log.info("t1线程状态:{}(预期为 TERMINATED), 是否存活: {}", t1.getState(), t1.isAlive());

        t1.join();
    }

}
