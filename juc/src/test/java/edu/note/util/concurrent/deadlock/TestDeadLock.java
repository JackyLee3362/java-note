package edu.note.util.concurrent.deadlock;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestDeadLockV1")
public class TestDeadLock {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.debug("Thread 1: lock A");
                sleep(1);
                synchronized (B) {
                    log.debug("Thread 1: lock B");
                    log.debug("Thread 1: Doing...");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.debug("Thread 2: lock B");
                sleep(0.5);
                synchronized (A) {
                    log.debug("Thread2: lock A");
                    log.debug("Thread2: Doing");
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}

