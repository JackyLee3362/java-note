package edu.note.thread.thread;

import edu.note.thread.util.Sleeper;
import java.lang.Thread.State;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/2 17:04
 */
@Slf4j(topic = "c.TestState")
public class ThreadStatusTest {

    @Test
    @DisplayName("1-New 状态")
    void testNew() {
        Thread t = new Thread(() -> {
        });
        Assertions.assertEquals(State.NEW, t.getState());
        log.debug("状态是 {}", t.getState());
    }

    @Test
    @DisplayName("2-Runnable 状态")
    void testRunnable() {
        Thread t = new Thread(() -> {
            while (true) {
            }
        });
        t.start();
        Assertions.assertEquals(State.RUNNABLE, t.getState());
        log.debug("状态是 {}", t.getState());
    }

    @Test
    @DisplayName("3-Blocked 状态")
    void testBlocked() {
        Thread t1 = new Thread(() -> {
            synchronized (ThreadStatusTest.class) {
                while (true) {
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (ThreadStatusTest.class) { // blocked
                log.info("running...");
            }
        });
        t2.start();
        Sleeper.sleep(0.1);
        Assertions.assertEquals(State.BLOCKED, t2.getState());
        log.debug("状态是 {}", t2.getState());
    }

    @Test
    @DisplayName("4-Waiting 状态")
    void testWaiting() {
        Thread t1 = new Thread(() -> {
            while (true) {
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                t1.join(); // waiting
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }, "t2");
        t2.start();
        Sleeper.sleep(0.2);
        Assertions.assertEquals(State.WAITING, t2.getState());
        log.debug("状态是 {}", t2.getState());
    }


    @Test
    @DisplayName("5-TimeWaiting 状态")
    void testTimedWaiting() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000000); // timed_waiting
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        });

        t.start();
        Sleeper.sleep(0.1);
        Assertions.assertEquals(State.TIMED_WAITING, t.getState());
        log.debug("状态是 {}", t.getState());
    }

    @Test
    @DisplayName("6-Terminated 状态")
    void testTerminated() {
        Thread t = new Thread(() -> {
        });
        t.start();

        Sleeper.sleep(0.5);
        Assertions.assertEquals(State.TERMINATED, t.getState());
        log.debug("状态是 {}", t.getState());
    }

}
