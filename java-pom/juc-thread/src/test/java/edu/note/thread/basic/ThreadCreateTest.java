package edu.note.thread.basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @description: 如何创建线程
 * @link: day01-03.001 创建线程
 * @return: null
 * @author: Jacky Lee
 * @date: 2024/3/30 11:22
 */
@Slf4j(topic = "c.ThreadCreateTest")
public class ThreadCreateTest {

    @Test
    @DisplayName("Thread 类创建")
    void testCreate01() throws InterruptedException {
        // 创建时设置线程名
        Thread t = new Thread(() -> log.debug("thread class: running..."));
        // 之后也可以修改
        t.setName("t1");
        t.start();
        t.join();
    }

    @Test
    @DisplayName("Runnable 接口创建")
    void testCreate02() throws InterruptedException {
        Runnable r = () -> log.debug("runnable interface: running");
        Thread t = new Thread(r, "t2");
        t.start();
        t.join();
    }

    @Test
    @DisplayName("FutureTask 创建")
    void testCreate03() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("using future task: running...");
            return 101;
        });
        Thread t = new Thread(task, "futureTaskDemo");
        t.start();
        // 获取返回值
        t.join();
        Assertions.assertEquals(101, task.get());
    }
}
