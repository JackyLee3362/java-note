package edu.note.util.concurrent.lock;

import edu.note.util.concurrent.mylock.MyLock;
import edu.note.thread.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/18 14:41
 */
@Slf4j(topic = "c.TestMyLock")
public class TestMyLock {

    public static void main(String[] args) {
        edu.note.util.concurrent.mylock.MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("t1: lock...");
                Sleeper.sleep(2);
            } finally {
                log.debug("t1: unlock...");
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("t2: lock...");
            } finally {
                log.debug("t2: unlock...");
                lock.unlock();
            }
        }, "t2").start();
    }

}
