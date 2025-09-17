package edu.note.util.concurrent.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.sellTickets")
public class SellTicketsReentrantLock implements Runnable {

    static int ticket = 0;

    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // 1.循环
        while (true) {
            // 2.同步代码块
            // synchronized (SellTicketsReentrantLock.class){
            lock.lock(); // 2 //3
            try {
                // 3.判断
                if (ticket == 100) {
                    break;
                    // 4.判断
                } else {
                    Thread.sleep(10);
                    ticket++;
                    log.info("{}在卖第{}张票！！！", Thread.currentThread().getName(), ticket);
                }
                // }
            } catch (InterruptedException e) {
                log.error("error");
            } finally {
                lock.unlock();
            }
        }
    }
}
