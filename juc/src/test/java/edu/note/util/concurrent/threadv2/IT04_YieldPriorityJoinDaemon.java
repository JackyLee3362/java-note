package edu.note.util.concurrent.threadv2;


import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * setPriority(int newPriority) 设置线程的优先级
 * final int getPriority() 获取线程的优先级
 */
@Slf4j(topic = "c.TestYield")
public class IT04_YieldPriorityJoinDaemon {

    static final int COUNT = 2000;

    Runnable r1 = () -> {
        int count = 0;
        while (count < COUNT) {
            log.debug("{}", count++);
        }
    };
    Runnable r2 = () -> {
        int count = 0;
        while (count < COUNT) {
            Thread.yield();
            log.debug("{}", count++);
        }
    };

    @Test
    public void testWithoutYield() throws InterruptedException {
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
    public void testWithYield() throws InterruptedException {
        Thread t1 = new Thread(r1, "🔴");
        Thread t2 = new Thread(r2, "🟡");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    // JVM 会在所有非守护线程执行完后退出，
    // t1 设置为守护线程后，即使线程未执行完，也会退出
    public static void main(String[] args) {

        log.debug("开始运行...");
        Thread t1 = new Thread(() -> {
            log.debug("开始运行...");
            Sleeper.sleep(2);
            log.debug("运行结束...");
        }, "daemon");

        // 设置该线程为守护线程
        t1.setDaemon(true);
        t1.start();

        Sleeper.sleep(1);
        log.debug("运行结束...");
    }

    @Test
    @DisplayName("Join")
    public void test1() throws InterruptedException {
        Runnable mr = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
        };
        Thread t1 = new Thread(mr, "🔴");
        t1.start();
        t1.join();

        // main 等待t1线程执行完毕
        // 然后执行下面语句
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

