package edu.note.util.concurrent.threadv2;

import edu.note.util.concurrent.util.Sleeper;
import java.lang.Thread.State;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/2 10:18
 */
@Slf4j(topic = "c.TestInterrupt")
public class IT05_Interrupt {

    /**
     * 打断正常运行的线程，打断标记是 true
     */
    @RepeatedTest(10)
    void interruptRunnableThread() throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.debug("Thread 线程被打断了");
                    break;
                }
            }
        }, "🔴");

        t.start();
        Sleeper.sleep(0.5);
        t.interrupt();
        t.join();
        Assertions.assertTrue(t.isInterrupted());
        Assertions.assertEquals(State.TERMINATED, t.getState());
        Assertions.assertFalse(t.isAlive());
    }

    /**
     * 打断 sleep 中的线程，打断标记是 false
     */
    @RepeatedTest(10)
    void interruptSleepThread() throws InterruptedException {
        Thread t = new Thread(() -> {
            log.info("开始 sleep");
            Sleeper.sleep(10);
            log.info("结束 sleep");
        }, "🔵");

        t.start();
        Sleeper.sleep(0.5);

        t.interrupt();
        t.join();
        Assertions.assertFalse(t.isInterrupted());
        Assertions.assertEquals(State.TERMINATED, t.getState());
        Assertions.assertFalse(t.isAlive());
    }

    /**
     * 打断 park 中的线程，打断标记是 true
     */
    @RepeatedTest(10)
    void interruptPark() throws InterruptedException {
        Thread t = new Thread(() -> {
            Assertions.assertFalse(Thread.currentThread().isInterrupted());
            LockSupport.park();
            Assertions.assertTrue(Thread.currentThread().isInterrupted());
        }, "test");

        t.start();
        Sleeper.sleep(0.5);
        t.interrupt();
        t.join();
        Assertions.assertTrue(t.isInterrupted());
        Assertions.assertEquals(State.TERMINATED, t.getState());
        Assertions.assertFalse(t.isAlive());
    }

    /**
     * 如果 interrupt 标记为 true，park 还能被正常运行吗？答：不行 park 只能在 interrupt 标记为 false 时阻塞
     */
    @RepeatedTest(10)
    void interruptParkWithTrueFlag() throws InterruptedException {
        Thread t = new Thread(() -> {
            // 一开始打断标记是 false
            Assertions.assertFalse(Thread.currentThread().isInterrupted());
            LockSupport.park();
            // 打断标记是 true
            Assertions.assertTrue(Thread.currentThread().isInterrupted());
            LockSupport.park();
            // 打断标记是 true
            Assertions.assertTrue(Thread.currentThread().isInterrupted());
        }, "test");

        t.start();
        Sleeper.sleep(0.5);
        t.interrupt();
        t.join();
    }

    @Test
    void interruptParkWithFalseFlag() throws InterruptedException {
        Thread t1 = new Thread(LockSupport::park, "t1");
        Thread t2 = new Thread(() -> {
            log.debug("t1 状态：{}", t1.isInterrupted());
            Sleeper.sleep(2);
            LockSupport.unpark(t1);
        }, "t2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.debug("t1 状态：{}", t1.isInterrupted());
    }
}
