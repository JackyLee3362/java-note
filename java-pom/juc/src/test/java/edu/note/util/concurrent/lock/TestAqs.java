package edu.note.util.concurrent.lock;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestAqs")
public class TestAqs {
    public static void main(String[] args) {
        MyAqsLock lock = new MyAqsLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
                sleep(1);
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        }, "t2").start();
    }
}

