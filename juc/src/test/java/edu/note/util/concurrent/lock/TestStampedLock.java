package edu.note.util.concurrent.lock;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc: 写锁+读锁+乐观读锁
 */
@Slf4j(topic = "c.TestStampedLock")
public class TestStampedLock {

    public static void main(String[] args) {
        DataContainerStamped dataContainer = new DataContainerStamped(1);

        new Thread(() -> {
            dataContainer.read(1);
        }, "t1").start();

        sleep(0.5);

        new Thread(() -> {
            dataContainer.read(0);
        }, "t2").start();
    }
}

