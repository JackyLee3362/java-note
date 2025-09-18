package edu.note.util.concurrent.lock;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

// 乐观锁
@Slf4j(topic = "c.CASTest")
public class CASTest {

    // 0 没加锁
    // 1 加锁
    private final AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                log.info("{} set value", Thread.currentThread().getName());
                break;
            }
        }
    }

    public void unlock() {
        log.info("{} unlock...", Thread.currentThread().getName());
        state.set(0);
    }

    @Test
    void testCas() throws InterruptedException {
        CASTest lock = new CASTest();

        Thread t1 = new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
                Sleeper.sleep(1);
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        Sleeper.sleep(0.5);
        t2.start();
        t1.join();
        t2.join();
    }

}
