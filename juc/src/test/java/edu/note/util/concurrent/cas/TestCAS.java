package edu.note.util.concurrent.cas;

import edu.note.util.concurrent.util.Sleeper;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.Test42")
public class TestCAS {

    // 0 没加锁
    // 1 加锁
    private final AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    public void unlock() {
        log.debug("unlock...");
        state.set(0);
    }

    @Test
    void testCas() throws InterruptedException {
        TestCAS lock = new TestCAS();

        Thread t1 = new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
                Sleeper.sleep(1);
            } finally {
                lock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
            } finally {
                lock.unlock();
            }
        }, "t2");

        t1.start();
        Sleeper.sleep(.5);
        t2.start();
        t1.join();
        t2.join();
    }

}
