package edu.note.util.concurrent.lock;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @desc: 写锁+读锁+乐观读锁
 */
@Slf4j(topic = "c.StampedLockTest")
public class StampedLockTest {

    static DataContainerStamped dataContainer;
    ExecutorService pool = Executors.newFixedThreadPool(5);
    Callable<Integer> readTask = () -> dataContainer.read();
    Runnable writeTask = () -> dataContainer.write(1);

    @BeforeEach
    void setUp() {
        dataContainer = new DataContainerStamped(10000);
    }

    @Test
    @DisplayName("多个【乐观读锁】不互斥")
    void testOptimisticRead() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            // 读任务
            Future<Integer> submit = pool.submit(readTask);
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.MINUTES);
        Assertions.assertEquals(10000, dataContainer.getData());
    }

    @Test
    @DisplayName("【乐观读锁】Validate 失败，升级为读锁")
    void testReadLock() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            // 读任务
            Future<Integer> submit = pool.submit(readTask);
            // 写任务
            pool.submit(writeTask);
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.MINUTES);
        Assertions.assertEquals(10010, dataContainer.getData());
    }

    @Test
    @DisplayName("写锁不可重入")
    void testReEntryWriteLock(){
        // 不可重入，会造成死锁
        // dataContainer.writeReEntry(1);
    }

}
