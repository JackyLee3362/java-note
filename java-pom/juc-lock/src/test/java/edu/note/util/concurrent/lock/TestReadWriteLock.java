package edu.note.util.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestReadWriteLock")
public class TestReadWriteLock {
    public static void main(String[] args) throws InterruptedException {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.read();
        }, "t1").start();

        new Thread(() -> {
            dataContainer.read();
        }, "t2").start();
    }
}

