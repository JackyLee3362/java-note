package edu.note.util.concurrent.lock;


import edu.note.thread.util.Sleeper;
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
        log.debug("乐观读锁获取 stamp={}", stamp);
        Sleeper.sleep(readTime);
        if (lock.validate(stamp)) {
            log.debug("乐观读锁校验成功 stamp={}, 数据={}", stamp, data);
            return data;
        }
        // 如果 validate 失败，进行锁升级
        // 锁升级 - 读锁
        log.debug("乐观读锁校验不成功 stamp = {}, 升级为读锁", stamp);
        try {
            stamp = lock.readLock();
            log.debug("读锁 Lock stamp={}", stamp);
            Sleeper.sleep(readTime);
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
     * @param newData
     */
    public void write(int newData, int writeTime) {
        long stamp = lock.writeLock();
        log.debug("写锁 Lock stamp={}", stamp);
        try {
            Sleeper.sleep(writeTime);
            this.data = newData;
        } finally {
            log.debug("写锁 UnLock stamp={}", stamp);
            lock.unlockWrite(stamp);
        }
    }
}
