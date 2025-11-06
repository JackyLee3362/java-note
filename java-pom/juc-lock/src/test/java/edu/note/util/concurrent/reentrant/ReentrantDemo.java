package edu.note.util.concurrent.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentranctLock 特点就是可重入
 * Re Entry Lock
 *
 * @author jackylee
 * @date 2025-11-06 09:38
 */
@Slf4j(topic = "c.TestReentrant")
public class ReentrantDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        lock.lock();
        try {
            log.debug("执行方法 1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {
            log.debug("执行方法 2");
            method3();
        } finally {
            lock.unlock();
        }
    }

    public static void method3() {
        lock.lock();
        try {
            log.debug("执行方法 3");
        } finally {
            lock.unlock();
        }
    }
}
