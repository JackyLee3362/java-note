package edu.note.util.concurrent.reentrant;

import static edu.note.thread.util.Sleeper.sleep;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.fairDemo")
public class ReentrantFairDemo {
    public static void main(String[] args) throws InterruptedException {

        // false 非公平锁，即后来的线程有可能直接插队获得锁，不一定按照请求的顺序
        // ReentrantLock lock = new ReentrantLock(false);

        // true 公平锁，按照请求的顺序获得锁
        ReentrantLock lock = new ReentrantLock(true);

        lock.lock();
        // 20 个线程去争夺锁
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.info("{} is running...", Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }, "thread" + i).start();
        }

        sleep(1);

        // 1s 之后开启 5 个线程去争抢锁
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.info("{} get lock...", Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            }, "force-" + i).start();
        }
        lock.unlock();
    }
}
