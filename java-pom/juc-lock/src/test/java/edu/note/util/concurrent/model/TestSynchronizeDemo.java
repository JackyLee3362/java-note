package edu.note.util.concurrent.model;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/*
 * 电影院卖票模型
 * 共有100张票
 * 有3个窗口卖票
 */
public class TestSynchronizeDemo {


    @Test
    @DisplayName("使用 synchronize 同步")
    void testUsingSynchronize() throws InterruptedException {

        Runnable runnable = new SellTicketsSynchronized();

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }

    @Test
    void test() throws InterruptedException {
        Runnable runnable = new SellTicketsReentrantLock();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    @Slf4j(topic = "c.SellTickets")
    static public class SellTicketsSynchronized implements Runnable {

        static int ticket = 0;

        @Override
        public void run() {
            // 1.循环
            while (true) {
                // 2.同步代码块
                synchronized (SellTicketsReentrantLock.class) {
                    // 3.判断
                    if (ticket == 100) {
                        break;
                        // 4.判断
                    } else {
                        Sleeper.sleep(0.01);
                        ticket++;
                        log.info("{}在卖第{}张票！！！", Thread.currentThread().getName(), ticket);
                    }
                }
            }
        }
    }

    @Slf4j(topic = "c.sellTickets")
    static public class SellTicketsReentrantLock implements Runnable {

        static int ticket = 0;

        static Lock lock = new ReentrantLock();

        @Override
        public void run() {
            // 1.循环
            while (true) {
                // 2.同步代码块
                lock.lock(); // 2 //3
                try {
                    // 3.判断
                    if (ticket == 100) {
                        break;
                        // 4.判断
                    } else {
                        Sleeper.sleep(0.01);
                        ticket++;
                        log.info("{}在卖第{}张票！！！", Thread.currentThread().getName(), ticket);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}