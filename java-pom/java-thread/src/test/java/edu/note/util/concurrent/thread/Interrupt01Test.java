package edu.note.util.concurrent.thread;

import edu.note.util.concurrent.util.Sleeper;
import java.lang.Thread.State;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.Interrupt01Test")
public class Interrupt01Test {

    /**
     * @description 打断 sleep 中的线程，抛出 InterruptedException，重置打断标记为 false
     * @author Jacky Lee
     * @date 2024/4/1 1:01
     */
    @RepeatedTest(10)
    @DisplayName("打断 sleep 线程")
    void test1() {
        Thread t1 = new Thread(() -> {
            log.info("开始");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                log.info("被打断");
                Assertions.assertFalse(Thread.currentThread().isInterrupted());
            }
            log.info("结束");
        }, "t1");
        t1.start();
        t1.interrupt();
        // Sleeper.sleep(0.2);
        // Assertions.assertEquals(State.TERMINATED, t1.getState());
        // Assertions.assertFalse(t1.isAlive());
    }

    /**
     * @description: 打断正常运行的线程，打断标记是 true
     * @author: Jacky Lee
     * @date: 2024/4/1 1:03
     */
    @RepeatedTest(10)
    @DisplayName("打断正常运行的线程")
    void test2() {
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
    }

    /**
     * @description 使用 interrupt 打断 park 中的线程
     * @author Jacky Lee
     * @date 2024/4/1 1:04
     */
    @RepeatedTest(10)
    @DisplayName("打断 park 线程")
    void test3() {
        Thread t1 = new Thread(() -> {
            Assertions.assertFalse(Thread.interrupted());
            LockSupport.park();
        });
        t1.start();

        Sleeper.sleep(0.2);
        t1.interrupt();
        Assertions.assertTrue(t1.isInterrupted(), "打断 park, 打断标记为 true");
        // Sleeper.sleep(0.2);
        // Assertions.assertEquals(State.TERMINATED, t1.getState());
        // Assertions.assertFalse(t1.isAlive());
    }

    /**
     * @description: 取消 park, 打断标记为 false
     * @author Jacky Lee
     * @date: 2024/4/1 1:05
     */
    @Test
    @DisplayName("取消 park")
    void test4() {
        Thread t1 = new Thread(() -> {
            Assertions.assertFalse(Thread.interrupted());
            LockSupport.park();
        });
        t1.start();

        Sleeper.sleep(0.5);
        LockSupport.unpark(t1);
        Assertions.assertFalse(t1.isInterrupted(), "取消 park, 打断标记为 false");

        log.info("结束");
    }

}
