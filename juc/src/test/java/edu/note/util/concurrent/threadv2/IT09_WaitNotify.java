package edu.note.util.concurrent.threadv2;


import com.jackylee.juc.util.Sleeper;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/2 19:21
 */

@Slf4j(topic = "c.TestWaitNotify")
public class IT09_WaitNotify {

    final static Object lock = new Object();

    public static void printWaitingThreads(Object lock) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);

        for (ThreadInfo threadInfo : threadInfos) {
            if (threadInfo != null && threadInfo.getLockName() != null) {
                String lockName = threadInfo.getLockName();
                if (lockName.equals(lock.toString())) {
                    log.debug("Thread {} is waiting on lock {}", threadInfo.getThreadId(), lockName);
                }
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
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
        Thread t1 = new Thread(runnable, "🔴");
        t1.start();

        Thread t2 = new Thread(runnable, "🟡");
        t2.start();

        // 主线程 2 秒后执行
        Sleeper.sleep(1);
        log.debug("wake up");
        printWaitingThreads(lock);
        synchronized (lock) {
            lock.notify(); // 唤醒obj上一个线程
            // lock.notifyAll(); // 唤醒obj上所有等待线程
        }
        printWaitingThreads(lock);

        t1.join();
        t2.join();

    }
}
