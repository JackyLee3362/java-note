package edu.note.util.concurrent.lock;

import static edu.note.thread.util.Sleeper.sleep;

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

