package edu.note.util.concurrent.synchronize;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestSynchronized02")
public class TestSynchronized {
    static final Object lock = new Object();

    public static void main(String[] args) {

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }
}
