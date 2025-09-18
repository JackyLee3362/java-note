package edu.note.thread.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.InterruptTest")
public class InterruptTest {

    @Test
    void test01(){
        // 打断自身
        Assertions.assertFalse(Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        // 不清除打断标记
        Assertions.assertTrue(Thread.currentThread().isInterrupted());
        // 清除打断标记
        Assertions.assertTrue(Thread.interrupted());
        Assertions.assertFalse(Thread.currentThread().isInterrupted());

    }

    /**
     * @description 打断 sleep 中的线程，抛出 InterruptedException，重置打断标记为 false
     * @author Jacky Lee
     * @date 2024/4/1 1:01
     */
    // @RepeatedTest(10)
    @Test
    @DisplayName("打断 sleep 线程")
    void test02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("开始");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.info("被打断");
                Assertions.assertFalse(Thread.currentThread().isInterrupted());
            }
            log.info("结束");
        });
        t1.start();
        t1.interrupt();
        // Sleeper.sleep(0.2);
        // Assertions.assertEquals(State.TERMINATED, t1.getState());
        // Assertions.assertFalse(t1.isAlive());
        t1.join();
    }

    /**
     * @description: 打断正常运行的线程，打断标记是 true
     * @author: Jacky Lee
     * @date: 2024/4/1 1:03
     */
    // @RepeatedTest(10)
    @Test
    @DisplayName("打断正常运行的线程")
    void test03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    log.debug("打断");
                    break;
                }
            }
        }, "t1");
        t1.start();
        t1.interrupt();
        // Sleeper.sleep(0.2);
        // Assertions.assertEquals(State.TERMINATED, t1.getState());
        // Assertions.assertFalse(t1.isAlive());
        t1.join();
    }


}
