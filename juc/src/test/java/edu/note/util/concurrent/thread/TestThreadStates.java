package edu.note.util.concurrent.thread;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestState")
public class TestThreadStates {
    public static void main(String[] args) throws IOException {
        showNew_t1();
        showRunnable_t2();
        showTerminated_t3();
        showTimeWaiting_t4();
        showWaiting_t5();
        showBlocked_t6();
        System.in.read();
    }

    private static void showTerminated_t3() {
        Thread t3 = new Thread(() -> {
            // log.debug("running...");
        }, "t3");
        t3.start();

        sleep(0.5);
        log.debug("t3 state {}", t3.getState());
    }

    private static void showBlocked_t6() {
        Thread t6 = new Thread(() -> {
            synchronized (TestThreadStates.class) { // blocked
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        });
        t6.start();
        sleep(0.1);
        log.debug("t6 state {}", t6.getState());
    }

    private static void showTimeWaiting_t4() {

        Thread t4 = new Thread(() -> {
            synchronized (TestThreadStates.class) {
                try {
                    Thread.sleep(1000000); // timed_waiting
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        });

        t4.start();
        sleep(0.1);
        log.debug("t4 state {}", t4.getState());
    }

    private static void showRunnable_t2() {

        Thread t2 = new Thread(() -> {
            while (true) {
            }
        }, "t2");
        t2.start();
        log.debug("t2 state {}", t2.getState());
    }

    private static void showWaiting_t5() {
        Thread t = new Thread(() -> {
            while (true)
                ;
        });
        Thread t5 = new Thread(() -> {
            try {
                t.join(); // waiting
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }, "t5");
        t5.start();
        sleep(0.1);
        log.debug("t5 state {}", t5.getState());

    }

    private static void showNew_t1() {
        Thread t1 = new Thread(() -> {
            log.debug("running...");
        }, "t1");

        log.debug("t1 state {}", t1.getState());
    }
}
