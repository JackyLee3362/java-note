package edu.note.thread.executor;

import edu.note.thread.util.Sleeper;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.ShutDownTest")
public class ShutDownTest {

    Runnable task1 = () -> {
        log.debug("task 1 running...");
        Sleeper.sleep(1);
        log.debug("task 1 finish...");
    };
    Runnable task2 = () -> {
        log.debug("task 2 running...");
        Sleeper.sleep(2);
        log.debug("task 2 finish...");
    };
    Runnable task3 = () -> {
        log.debug("task 3 running...");
        Sleeper.sleep(3);
        log.debug("task 3 finish...");
    };

    @Test
    @DisplayName("测试 shutdown")
    void test01() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(task1);
        pool.submit(task2);
        pool.submit(task3);

        log.debug("shutdown");
        // 允许已提交的任务继续执行(包括被阻塞的任务)，但是不接收新任务
        pool.shutdown();
        // 一般和 shutdown 配置，最长等待时间
        boolean b = pool.awaitTermination(5, TimeUnit.SECONDS);
        Assertions.assertTrue(b);
    }

    @Test
    @DisplayName("测试 awaitTermination")
    void test02() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(task1);
        pool.submit(task2);
        pool.submit(task3);

        log.debug("shutdown + awaitTermination");
        // 允许已提交的任务继续执行(包括被阻塞的任务)，但是不接收新任务
        pool.shutdown();
        // 一般和 shutdown 配置，最长等待时间
        boolean b = pool.awaitTermination(2, TimeUnit.SECONDS);
        Assertions.assertFalse(b);
    }

    @Test
    @DisplayName("测试 shutdownNow")
    void test03() {
        // 试图立刻停止所有正在执行的任务，不处理阻塞中的任务，并返回没有完成的任务列表
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(task1);
        pool.submit(task2);
        pool.submit(task3);

        log.debug("shutdownNow");
        // 试图立刻停止所有正在执行的任务，并返回没有完成的任务列表
        List<Runnable> runnable = pool.shutdownNow();
        log.debug("other.... {}", runnable);
    }
}
