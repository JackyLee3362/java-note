package edu.note.java.juc.issue;


import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDeadLock {

    @Test
    void test1() throws InterruptedException {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.debug("Thread 1: lock A");
                Sleeper.sleep(1);
                synchronized (B) {
                    log.debug("Thread 1: lock B");
                    log.debug("Thread 1: Doing...");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.debug("Thread 2: lock B");
                Sleeper.sleep(0.5);
                synchronized (A) {
                    log.debug("Thread2: lock A");
                    log.debug("Thread2: Doing");
                }
            }
        }, "t2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
