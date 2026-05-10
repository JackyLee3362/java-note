package edu.note.java.juc.thread;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.locks.LockSupport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jacky Lee
 * @date 2024/4/1 1:04
 */
@Slf4j
public class ThreadParkTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("t1: start...");
            Sleeper.sleep(2);
            log.info("t1: park...");
            LockSupport.park();
            log.info("t1: resume...");
        }, "t1");
        t1.start();

        Sleeper.sleep(1);
        log.info("unpark...");

        // 比较注释这段和不注释这段的运行结果
        LockSupport.unpark(t1);
    }

    @Test
    @DisplayName("打断 park 线程")
    void test3() {
        Thread t1 = new Thread(LockSupport::park);
        t1.start();

        Sleeper.sleep(0.2);

        log.info("park 后, 打断标记为 {}(预期为false)", t1.isInterrupted());
        t1.interrupt();
        Thread.interrupted();
        log.info("自身打断 park, 打断标记为 {}(预期为true)", t1.isInterrupted());
        // Sleeper.sleep(0.2);
        // assertEquals(State.TERMINATED, t1.getState());
        // assertFalse(t1.isAlive());
    }

    // 取消 park, 打断标记为 false
    @Test
    @DisplayName("取消 park")
    void test4() {
        Thread t1 = new Thread(LockSupport::park);
        t1.start();
        Sleeper.sleep(0.5);
        LockSupport.unpark(t1);

        assertFalse(t1.isInterrupted(), "取消 park, 打断标记为 false");
        log.info("结束");
    }
}
