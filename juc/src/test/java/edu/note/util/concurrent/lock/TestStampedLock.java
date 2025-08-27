package edu.note.util.concurrent.lock;


import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @desc: 写锁+读锁+乐观读锁
 */
@Slf4j(topic = "c.TestStampedLock")
public class TestStampedLock {

    @Test
    @DisplayName("多个【乐观读锁】不互斥")
    public void testOptimisticRead() throws InterruptedException {
        DataContainerStamped dataContainer = new DataContainerStamped(1);
        Thread reader1 = new Thread(() -> {
            int read = dataContainer.read(1);
            Assertions.assertEquals(1, read);
        }, "读线程-1");
        Thread reader2 = new Thread(() -> {
            int read = dataContainer.read(1);
            Assertions.assertEquals(1, read);
        }, "读线程-2");

        reader1.start();
        Sleeper.sleep(0.5);
        reader2.start();

        reader1.join();
        reader2.join();
    }

    @Test
    @DisplayName("【乐观读锁】Validate 失败，升级为读锁")
    public void testReadLock() throws InterruptedException {
        DataContainerStamped dataContainer = new DataContainerStamped(1);

        Thread reader = new Thread(() -> {
            int read = dataContainer.read(2);
            Assertions.assertEquals(1, read);
        }, "读线程");

        Thread writer = new Thread(() -> {
            dataContainer.write(1, 1);
        }, "写线程");

        reader.start();
        Sleeper.sleep(0.5);
        writer.start();

        reader.join();
        writer.join();
    }

    @Test
    @DisplayName("【乐观读锁】升级读锁 - 与【写锁】互斥")
    public void testWrite() throws InterruptedException {
        DataContainerStamped dataContainer = new DataContainerStamped(1);

        Thread reader = new Thread(() -> {
            int read = dataContainer.read(1);
        }, "读线程");

        Thread writer = new Thread(() -> {
            dataContainer.write(2, 2);
        }, "写线程");

        reader.start();
        Sleeper.sleep(0.5);
        writer.start();

        reader.join();
        writer.join();
    }


}


