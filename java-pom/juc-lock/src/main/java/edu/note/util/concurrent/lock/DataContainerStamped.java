package edu.note.util.concurrent.lock;


import edu.note.thread.util.Sleeper;
import java.util.concurrent.locks.StampedLock;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/8/26 20:31
 */
@Slf4j(topic = "c.DataContainerStamped")
class DataContainerStamped {

    @Getter
    private int data;

    // 写锁不可重入
    private final StampedLock lock = new StampedLock();
    public static final int READ_TIME = 1000;

    public DataContainerStamped(int data) {
        this.data = data;
    }

    /**
     * 读锁 + 乐观读锁
     *
     * @return
     */
    public int read() {
        long stamp = lock.tryOptimisticRead();
        log.debug("乐观读锁获取 stamp={}", stamp);
        Sleeper.sleepRandom(READ_TIME);
        if (lock.validate(stamp)) {
            log.debug("乐观读锁校验成功 stamp={}, 数据={}", stamp, data);
            return data;
        }
        // 如果 validate 失败，进行锁升级
        // 锁升级 - 读锁
        log.info("乐观读锁校验不成功, 升级为读锁, stamp = {}", stamp);
        try {
            stamp = lock.readLock();
            log.debug("读锁 Lock stamp={}", stamp);
            Sleeper.sleepRandom(READ_TIME);
            log.debug("读锁 读取完成 stamp={}, 数据={}", stamp, data);
            return data;
        } finally {
            log.debug("读锁 UnLock stamp={}", stamp);
            lock.unlockRead(stamp);
        }
    }

    /**
     * 写锁
     *
     * @param increment
     */
    public void write(int increment) {
        long stamp = lock.writeLock();
        log.debug("写锁 Lock stamp={}", stamp);
        try {
            Sleeper.sleepRandom(READ_TIME);
            this.data += increment;
        } finally {
            log.debug("写锁 UnLock stamp={}, data={}", stamp, data);
            lock.unlockWrite(stamp);
        }
    }

    /**
     * 写锁
     *
     * @param increment
     */
    public void writeReEntry(int increment) {
        long stamp = lock.writeLock();
        try {
            lock.writeLock();
            this.data += increment;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
