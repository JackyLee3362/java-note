package edu.note.thread.thread;


import edu.note.thread.util.Sleeper;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.ParkTest")
public class ParkTest {

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
        log.debug("un-park...");

        // 比较注释这段和不注释这段的运行结果
        LockSupport.unpark(t1);
    }

    /**
     * @description 使用 interrupt 打断 park 中的线程
     * @author Jacky Lee
     * @date 2024/4/1 1:04
     */
    @RepeatedTest(10)
    @DisplayName("打断 park 线程")
    void test3() {
        Thread t1 = new Thread(LockSupport::park);
        t1.start();

        Sleeper.sleep(0.2);
        Assertions.assertFalse(t1.isInterrupted(), "park 后, 打断标记为 false");
        t1.interrupt();
        Assertions.assertTrue(t1.isInterrupted(), "打断 park, 打断标记为 true");
        // Sleeper.sleep(0.2);
        // Assertions.assertEquals(State.TERMINATED, t1.getState());
        // Assertions.assertFalse(t1.isAlive());
    }

    /**
     * @description: 取消 park, 打断标记为 false
     * @author Jacky Lee
     * @date: 2024/4/1 1:05
     */
    @Test
    @DisplayName("取消 park")
    void test4() {
        Thread t1 = new Thread(LockSupport::park);
        t1.start();

        Sleeper.sleep(0.5);
        LockSupport.unpark(t1);
        Assertions.assertFalse(t1.isInterrupted(), "取消 park, 打断标记为 false");

        log.info("结束");
    }
}
