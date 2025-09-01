package edu.note.util.concurrent.thread;

import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.Test6")
public class TestThreadSleep {

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