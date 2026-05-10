package edu.note.java.juc.lock.lock;

import edu.note.java.juc.lock.lock.demo.MyAqsLock;
import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AqsTest {

    // TODO 不理解这个 Demo
    public static void main(String[] args) {
        MyAqsLock lock = new MyAqsLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
                Sleeper.sleep(1);
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        }).start();
    }
}

