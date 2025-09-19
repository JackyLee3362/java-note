package edu.note.util.concurrent.lock;

import edu.note.thread.util.Sleeper;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.DataContainer")
public class DataContainer {

    private int data;
    private final Random RANDOM = new Random();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    DataContainer(int data) {
        this.data = data;
    }

    public int read() {
        log.debug("获取读锁...");
        readLock.lock();
        try {
            Sleeper.sleepRandom(1000);
            log.debug("读取数据 {} ", data);
            return data;
        } finally {
            log.debug("释放读锁...");
            readLock.unlock();
        }
    }

    public void write(int increment) {
        log.debug("获取写锁...");
        writeLock.lock();
        try {
            Sleeper.sleepRandom(1000);
            data += increment;
            log.debug("写入数据, {}", data);
        } finally {
            log.debug("释放写锁...");
            writeLock.unlock();
        }
    }
}
