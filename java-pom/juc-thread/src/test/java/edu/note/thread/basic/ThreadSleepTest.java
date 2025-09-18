package edu.note.thread.basic;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.Test6")
public class ThreadSleepTest {

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