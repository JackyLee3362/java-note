package edu.note.util.concurrent.thread;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    /**
     * @description 测试打断 sleep 中的线程，打断标记是什么，答：是 false
     * @author Jacky Lee
     * @date 2024/4/1 1:01
     */
    @Test
    void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("开始");
            sleep(10);
            log.info("结束");
        }, "t1");
        t1.start();

        sleep(0.5);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

    /**
     * @description: 测试，打断正常运行的线程，打断标记是什么？答：是 true
     * @author: Jacky Lee
     * @date: 2024/4/1 1:03
     */
    @Test
    void test2() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if (interrupted) {
                    log.debug(" 打断状态: {}", interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();

        sleep(0.5);
        t2.interrupt();
    }


    /**
     * @description 测试打断 park 中的线程，打断标记是什么？答：是 true
     * @author Jacky Lee
     * @date 2024/4/1 1:04
     */
    @Test
    void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();


        sleep(0.5);
        t1.interrupt();
    }

    /**
     * @description: 测试：如果 interrupt 标记为 true，park还能被正常运行吗？答：false
     * @author Jacky Lee
     * @date: 2024/4/1 1:05
     */
    @Test
    void test4() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                LockSupport.park();
                log.debug("打断状态：{}", Thread.interrupted());
            }
        });
        t1.start();

        sleep(1);
        t1.interrupt();
    }

}
