package edu.note.java.juc.lock.lock.demo;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataContainer {

    private int data;
    private final Random RANDOM = new Random();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public DataContainer(int data) {
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
