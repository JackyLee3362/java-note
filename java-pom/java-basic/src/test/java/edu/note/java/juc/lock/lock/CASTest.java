package edu.note.java.juc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

// CAS 乐观锁
@Slf4j
public class CASTest {

    /**
     * 0 没加锁 1 加锁
     */
    private final AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        log.info("{} try to get LOCK", Thread.currentThread().getName());
        while (true) {
            if (state.compareAndSet(0, 1)) {
                log.info("{} get LOCK...", Thread.currentThread().getName());
                break;
            }
        }
    }

    public void unlock() {
        log.info("{} release LOCK...", Thread.currentThread().getName());
        state.set(0);
    }

    @Test
    void testCas() throws InterruptedException {
        CASTest lock = new CASTest();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                Sleeper.sleep(1);
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                Sleeper.sleep(0.1);
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        Sleeper.sleep(0.1);
        t2.start();
        t1.join();
        t2.join();
    }

}
