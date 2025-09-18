package edu.note.util.concurrent.synchronize;

import edu.note.thread.util.Printer;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/2 19:21
 */

@Slf4j(topic = "c.TestWaitNotify")
public class TestWaitNotify {

    final static Object lock = new Object();

    @Test
    void test() throws InterruptedException {
        Runnable runnable = () -> {
            synchronized (lock) {
                log.debug("running...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                log.debug("others...");
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();

        // 主线程 1 秒后执行
        TimeUnit.SECONDS.sleep(1);
        log.debug("wake up");
        Printer.printWaitingThreads(lock);
        synchronized (lock) {
            // lock.notify(); // 唤醒obj上一个线程
            lock.notifyAll(); // 唤醒obj上所有等待线程
        }
        Printer.printWaitingThreads(lock);

        t1.join();
        t2.join();
    }
}
