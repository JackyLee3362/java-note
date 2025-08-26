package edu.note.util.concurrent.lock;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import java.util.concurrent.locks.StampedLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/8/26 20:31
 */
@Slf4j(topic = "c.DataContainerStamped")
class DataContainerStamped {

    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    /**
     * 读锁 + 乐观读锁
     *
     * @param readTime
     * @return
     */
    public int read(int readTime) {
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read locking...{}", stamp);
        sleep(readTime);
        if (lock.validate(stamp)) {
            log.debug("read finish...{}, data:{}", stamp, data);
            return data;
        }
        // 锁升级 - 读锁
        log.debug("updating to read lock... {}", stamp);
        try {
            stamp = lock.readLock();
            log.debug("read lock {}", stamp);
            sleep(readTime);
            log.debug("read finish...{}, data:{}", stamp, data);
            return data;
        } finally {
            log.debug("read unlock {}", stamp);
            lock.unlockRead(stamp);
        }
    }

    /**
     * 写锁
     *
     * @param newData
     */
    public void write(int newData) {
        long stamp = lock.writeLock();
        log.debug("write lock {}", stamp);
        try {
            sleep(2);
            this.data = newData;
        } finally {
            log.debug("write unlock {}", stamp);
            lock.unlockWrite(stamp);
        }
    }
}
