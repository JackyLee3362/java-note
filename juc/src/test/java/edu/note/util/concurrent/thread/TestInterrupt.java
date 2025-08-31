package edu.note.util.concurrent.thread;

import edu.note.util.concurrent.util.Sleeper;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {

    /**
     * @description 打断 sleep 中的线程，抛出 InterruptedException，重置打断标记为 false
     * @author Jacky Lee
     * @date 2024/4/1 1:01
     */
    @Test
    @DisplayName("打断 sleep 中的线程，打断标记是 True")
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
    }

    /**
     * @description: 打断正常运行的线程，打断标记是 True
     * @author: Jacky Lee
     * @date: 2024/4/1 1:03
     */
    @Test
    void test2() {
        Thread t2 = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if (interrupted) {
                    log.debug("打断");
                    break;
                }
            }
        }, "t2");
        t2.start();

        t2.interrupt();
    }

    /**
     * @description 测试打断 park 中的线程，打断标记是 true
     * @author Jacky Lee
     * @date 2024/4/1 1:04
     */
    @Test
    void test3() {
        Thread t1 = new Thread(() -> {
            Assertions.assertFalse(Thread.interrupted());
            LockSupport.park();
        });
        t1.start();

        Sleeper.sleep(0.5);

        t1.interrupt();
        Assertions.assertTrue(t1.isInterrupted());
        log.info("结束");
    }

    /**
     * @description: 使用 interrupt 打断 park 中的线程
     * @author Jacky Lee
     * @date: 2024/4/1 1:05
     */
    @Test
    void test4() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...{}", i);
                log.debug("打断前：{}", Thread.interrupted());
                LockSupport.park();
                log.debug("恢复后：{}", Thread.interrupted());
            }
        });
        t1.start();

        Sleeper.sleep(1);
        t1.interrupt();
        Assertions.assertTrue(t1.isInterrupted());
        Sleeper.sleep(1);
        LockSupport.unpark(t1);
        Assertions.assertFalse(t1.isInterrupted());

        log.info("结束");
    }

    @Test
    void test() {
        Thread t1 = new Thread(() -> {
            log.info("t1 暂停");
            LockSupport.park();
            Assertions.assertFalse(Thread.interrupted());
            log.info("t1 恢复");
        }, "t1");
        t1.start();

        Sleeper.sleep(0.5);
        log.info("主线程 unpark t1");
        LockSupport.unpark(t1);
    }

}
