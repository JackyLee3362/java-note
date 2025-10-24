package edu.note.thread.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

// public static ExecutorService newFixedThreadPool (int nThreads) 创建有上限的线程池
@Slf4j(topic = "c.ThreadPool")
public class ExecutorsV2Test {

    Runnable runnable = () -> {
        for (int i = 1; i <= 10; i++) {
            log.info("{}: i={}", Thread.currentThread(), i);
        }
    };

    @Test
    @DisplayName("测试")
    void testRunnable() {
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        // assertThrows(RuntimeException.class, () -> {
        //     thread.run();
        // });
        thread.start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(thread);

    }

    @Test
    @DisplayName("只有一个线程的线程池，即使有异常也不会停止")
    void test02() {
        // 核心线程数 = 最大线程数 = 1
        // 生存时间和时间单位无意义
        // 按 FIFO 先入先出的顺序执行队列中的任务
        // 默认丢弃策略
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 6; i++) {
            pool.submit(runnable);
        }
        pool.shutdown();
    }

    @Test
    @DisplayName("固定线程数量的线程池")
    void test2() {
        // 核心线程数 = 最大线程数 = N
        // 如果有空闲的核心线程，则执行
        // 1.获取线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(3);
        // 2.提交任务
        for (int i = 0; i < 6; i++) {
            pool.submit(runnable);
        }
        // 3.销毁线程池
        pool.shutdown();
        assertEquals(3, ((ThreadPoolExecutor) pool).getPoolSize());
    }

    @Test
    @DisplayName("无固定上限的线程池")
    void test1() {
        // 该方法返回一个可根据实际情况调整线程数量的线程池
        // 初始大小为0
        // 如果在一段时间内（默认为 60 秒）没有新任务提交，线程会因超时并被销毁
        // 队列是 SynchronousQueue 队列
        // 1.获取线程池对象
        ExecutorService pool = Executors.newCachedThreadPool();
        // 2.提交任务
        for (int i = 0; i < 9; i++) {
            pool.submit(runnable);
        }
        // 3.销毁线程池
        pool.shutdown();
        assertEquals(9, ((ThreadPoolExecutor) pool).getPoolSize());
    }

    @Test
    @DisplayName("定时任务")
    void test04() {
        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        // TODO
    }

}
