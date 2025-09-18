package edu.note.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// public static ExecutorService newFixedThreadPool (int nThreads) 创建有上限的线程池
@Slf4j(topic = "c.ThreadPool")
public class ExecutorsTest {

    Runnable runnable = () -> {
        for (int i = 1; i <= 100; i++) {
            log.info("{}: {}", Thread.currentThread(), i);
        }
    };


    @Test
    @DisplayName("固定线程数量的线程池")
    void test2() {
        // 该线程池中的线程数量始终不变。
        // 当有一个新的任务提交时
        // IF 线程池中若有空闲线程，则立即执行
        // IF NOT 则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务
        // 1.获取线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(3);
        // 2.提交任务
        for (int i = 0; i < 6; i++) {
            pool.submit(runnable);
        }
        // 3.销毁线程池
        pool.shutdown();
        Assertions.assertEquals(3, ((ThreadPoolExecutor) pool).getPoolSize());
    }


    @Test
    @DisplayName("只有一个线程的线程池")
    void test02() {
        // 若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，待线程空闲，按先入先出的顺序执行队列中的任务。
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 6; i++) {
            pool.submit(runnable);
        }
        pool.shutdown();
    }


    @Test
    @DisplayName("无固定上限的线程池")
    void test1() {
        // 该方法返回一个可根据实际情况调整线程数量的线程池。初始大小为0。
        // 当有新任务提交时，如果当前线程池中没有线程可用，它会创建一个新的线程来处理该任务。
        // 如果在一段时间内（默认为 60 秒）没有新任务提交，核心线程会超时并被销毁，从而缩小线程池的大小。
        // 1.获取线程池对象
        ExecutorService pool = Executors.newCachedThreadPool();
        // 2.提交任务
        for (int i = 0; i < 9; i++) {
            pool.submit(runnable);
        }
        // 3.销毁线程池
        pool.shutdown();
        Assertions.assertEquals(9, ((ThreadPoolExecutor) pool).getPoolSize());
    }

    @Test
    @DisplayName("定时任务")
    void test04() {

        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        pool.submit(runnable);
        pool.shutdown();

    }
}
