package edu.note.util.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.ReadWriteLockTest")
public class ReadWriteLockTest {

    ExecutorService pool = Executors.newFixedThreadPool(3);
    DataContainer container;
    Runnable readTask = () -> container.read();
    Runnable writeTask = () -> container.write(1);


    @BeforeEach
    void setUp() {
        container = new DataContainer(10000);
    }


    @Test
    @DisplayName("读写锁 - 多个读锁")
    void test01() throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            pool.submit(readTask);
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("读写锁 - 读+写")
    void test02() throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            pool.submit(readTask);
            pool.submit(writeTask);
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

}

