package edu.note.java.juc.thread;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadJoinTest {

    static int r0 = 0;
    static int r1 = 0;
    static int r2 = 0;

    Thread t1 = new Thread(() -> {
        Sleeper.sleep(1);
        r1 = 10;
    });
    Thread t2 = new Thread(() -> {
        Sleeper.sleep(2);
        r2 = 20;
    });

    @AfterEach
    void afterEach() {
        r0 = 0;
        r1 = 0;
        r2 = 0;
    }

    @Test
    @DisplayName("测试 join")
    void test_join() throws InterruptedException {

        long start = System.currentTimeMillis();

        t1.start();
        log.info("t1 join, 主线程需等待 t1 执行完成");
        t1.join();

        t2.start();
        log.info("t2 join, 主线程需等待 t2 执行完成");
        t2.join();

        long end = System.currentTimeMillis();

        log.info("r1:{}(预期10), r2:{}(预期20), cost:{}毫秒(预期3000毫秒)", r1, r2, end - start);
    }

    @Test
    @DisplayName("测试 join 限时等待")
    void test_join_with_time() throws InterruptedException {

        long start = System.currentTimeMillis();

        t2.start();
        log.info("t2 join, 主线程需等待 t2 执行完成, 最多等待1秒");
        t2.join(1000);

        long end = System.currentTimeMillis();

        log.info("r2:{}(期望20) cost: {} 毫秒(预期1000毫秒)", r2, end - start);
    }

}
