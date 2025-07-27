package edu.note.util.concurrent.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test6")
public class TestThreadSleep {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }, "t1");

        t1.start();
        log.debug("t1 state: {}", t1.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        log.debug("t1 state: {}", t1.getState());
    }
}
