package edu.note.util.concurrent.thread;

import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestDaemon2")
public class TestDaemon2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("结束");
        }, "t1");
        t1.setDaemon(true);
        t1.start();

        Sleeper.sleep(1);
        log.debug("结束");
    }
}
