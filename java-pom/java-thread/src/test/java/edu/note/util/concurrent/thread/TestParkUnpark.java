package edu.note.util.concurrent.thread;


import edu.note.util.concurrent.util.Sleeper;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("t1: start...");
            Sleeper.sleep(2);
            log.debug("t1: park...");
            LockSupport.park();
            log.debug("t1: resume...");
        }, "t1");
        t1.start();

        Sleeper.sleep(1);
        log.debug("unpark...");

        // 比较注释这段和不注释这段的运行结果
        // LockSupport.unpark(t1);
    }

}
