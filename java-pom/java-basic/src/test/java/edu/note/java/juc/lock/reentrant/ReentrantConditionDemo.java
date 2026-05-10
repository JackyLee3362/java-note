package edu.note.java.juc.lock.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReentrantConditionDemo {

    // 重入锁，保护临界区
    static ReentrantLock lock = new ReentrantLock();

    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();

    // 控制条件是否被满足，确保多线程可见性
    static volatile boolean hasObj1 = false;
    static volatile boolean hasObj2 = false;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                // 获得锁
                lock.lock();
                // 如果条件 1 没有满足
                while (!hasObj1) {
                    try {
                        // 释放可重入锁，线程挂在相应条件队列上
                        condition1.await();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                // 条件 1 满足
                log.debug("条件 1 满足");
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                while (!hasObj2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                log.debug("条件 2");
            } finally {
                lock.unlock();
            }
        }).start();

        Sleeper.sleep(1);
        sendObj2();
        Sleeper.sleep(1);
        sendObj1();
    }

    private static void sendObj1() {
        lock.lock();
        try {
            log.debug("满足条件 1");
            hasObj1 = true;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }

    private static void sendObj2() {
        lock.lock();
        try {
            log.debug("满足条件 2");
            hasObj2 = true;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }
}
