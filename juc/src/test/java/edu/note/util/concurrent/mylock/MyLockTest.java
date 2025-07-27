package edu.note.util.concurrent.mylock;

import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/18 14:41
 */
@Slf4j(topic = "c.MyLockTest")
public class MyLockTest {

    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            try {
                log.debug("lock...");
                Sleeper.sleep(2);
            } finally {
                log.debug("unlock...");
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("lock...");
            } finally {
                log.debug("unlock...");
                lock.unlock();
            }
        }, "t2").start();
    }

}
