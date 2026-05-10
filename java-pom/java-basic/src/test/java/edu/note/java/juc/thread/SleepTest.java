package edu.note.java.juc.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;

@Slf4j(topic = "c.Test6")
public class SleepTest {

    @Test
    void testThreadSleep() {

        Thread t = new Thread(() -> {
            Sleeper.sleep(2);
        }, "t1");

        log.debug("t1 state: {}", t.getState());
        t.start();
        log.debug("t1 state: {}", t.getState());

        Sleeper.sleep(1);
        log.debug("t1 state: {}", t.getState());
        Sleeper.sleep(2);
        log.debug("t1 state: {}", t.getState());
    }

}