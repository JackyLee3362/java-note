package edu.note.util.concurrent.lock;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.TestReadWriteLock")
public class TestReadWriteLock {

    @Test
    void test01() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        DataContainer container = new DataContainer();
        for (int i = 0; i < 10; i++) {
            int tmp = i;
            pool.submit(() -> {
                container.read();
                Sleeper.sleepRandom(1000);
            });
            pool.submit(() -> {
                container.read();
                Sleeper.sleepRandom(1000);
            });
            pool.submit(() -> {
                container.write(tmp);
                Sleeper.sleepRandom(1000);
            });
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}

