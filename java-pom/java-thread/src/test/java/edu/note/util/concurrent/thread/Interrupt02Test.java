package edu.note.util.concurrent.thread;

import edu.note.util.concurrent.util.Sleeper;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @author jackylee
 * @date 2024/12/2 10:18
 */
@Slf4j(topic = "c.Interrupt01Test")
public class Interrupt02Test {


    @RepeatedTest(10)
    void interruptParkWithTrueFlag01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
                log.debug("t1: start...");
                LockSupport.park();
                log.debug("t1: resume...");
            });

        t1.start();
        Sleeper.sleep(0.2);
        t1.interrupt();
        t1.join();
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

    @RepeatedTest(10)
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
