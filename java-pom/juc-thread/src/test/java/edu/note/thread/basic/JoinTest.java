package edu.note.thread.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.thread.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.JoinTest")
public class JoinTest {

    static int r0 = 0;
    static int r1 = 0;
    static int r2 = 0;

    @AfterEach
    void afterEach() {
        r0 = 0;
        r1 = 0;
        r2 = 0;
    }

    /**
     * @author: Jacky Lee
     * @date: 2024/3/31 23:43
     */
    @Test
    @DisplayName("测试 join 限时等待")
    void testJoinWithTime() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Sleeper.sleep(2);
            r1 = 10;
        });

        long start = System.currentTimeMillis();
        t1.start();

        // 线程执行结束会导致 join 结束
        t1.join(3000);
        long end = System.currentTimeMillis();

        assertEquals(10, r1);
        assertEquals(0, r2);
        assertEquals(0, r0);

        log.debug("r1={} r2={} cost: {}", r1, r2, end - start);
    }

    /**
     * @author: Jacky Lee
     * @date: 2024/3/31 23:44
     */
    @Test
    @DisplayName("测试 join")
    void testJoin_2_thread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Sleeper.sleep(1);
            r1 = 10;
        });
        Thread t2 = new Thread(() -> {
            Sleeper.sleep(2);
            r2 = 20;
        });
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.debug("join begin");
        t2.join();
        log.debug("t2 join end");
        t1.join();
        log.debug("t1 join end");
        long end = System.currentTimeMillis();
        assertEquals(10, r1);
        assertEquals(20, r2);
        assertEquals(0, r0);
        log.debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }

    /**
     * @author: Jacky Lee
     * @date: 2024/3/31 23:45
     */
    @Test
    @DisplayName("测试单个线程 join")
    void testJoin_1_thread() throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            Sleeper.sleep(1);
            log.debug("结束");
            r0 = 10;
        });
        t1.start();
        t1.join();

        assertEquals(10, r0);
        assertEquals(0, r1);
        assertEquals(0, r2);
        log.debug("结果为:{}", r0);
        log.debug("结束");
    }
}
