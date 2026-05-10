package edu.note.java.juc.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReentrantTimeoutDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {

        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            try {
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    log.debug("获取等待 1s 后失败，返回");
                    return;
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
           Sleeper.sleep(2);
        } finally {
            lock.unlock();
        }
    }

    private static void test2() {

        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            if (!lock.tryLock()) {
                log.debug("获取立刻失败，返回");
                return;
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
            Sleeper.sleep(2);
        } finally {
            lock.unlock();
        }
    }
}
