package edu.note.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// public static ExecutorService newFixedThreadPool (int nThreads) 创建有上限的线程池
public class IT01MyThreadPoolDemo {

    Runnable r1 = () -> {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    };

    @Test
    @DisplayName("创建无上限的线程池")
    void test1() {
        // 1.获取线程池对象
        ExecutorService pool1 = Executors.newCachedThreadPool();
        // 2.提交任务
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        // 3.销毁线程池
        pool1.shutdown();
        System.out.println(((ThreadPoolExecutor) pool1).getPoolSize());
    }

    @Test
    @DisplayName("创建有上限的线程池")
    void test2() {
        // 1.获取线程池对象
        ExecutorService pool1 = Executors.newFixedThreadPool(3);
        // 2.提交任务
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        pool1.submit(r1);
        // 3.销毁线程池
        pool1.shutdown();
        System.out.println(((ThreadPoolExecutor) pool1).getPoolSize());
    }
}
