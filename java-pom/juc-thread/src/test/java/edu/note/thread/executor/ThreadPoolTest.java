package edu.note.thread.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @param 核心线程数量     不能小于0
 * @param 最大线程数      不能小于0，最大数量 >= 核心线程数量
 * @param 空闲线程最大存活时间 不能小于0
 * @param 时间单位       用TimeUnit指定
 * @param 任务队列       不能为null
 * @param 创建线程工厂     Nullable
 * @param 任务的拒绝策略    不能为null
 */
public class ThreadPoolTest {

    @Test
    @DisplayName("线程池创建")
    void test01() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3/* 核心线程数 */, 6/* 最大线程数 */,
                60, TimeUnit.SECONDS, // 空闲线程最长存活时间是 60 秒
                new ArrayBlockingQueue<>(3), // 任务队列
                // 创建线程工厂
                Executors.defaultThreadFactory(),
                // 任务的拒绝策略(默认策略)，抛出异常
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static int cnt = 1;

    @Test
    @DisplayName("线程池示例")
    void test02() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            int temp = i;
            executor.execute(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + ":" + temp);
            });
        }
        executor.shutdown();
    }

    @Test
    @DisplayName("线程池示例2")
    void test03() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        Thread t = new Thread(() -> {
            synchronized (this) {
                String name = Thread.currentThread().getName();
                System.out.println(name + ":" + cnt);
                cnt += 1;
            }
        });
        for (int i = 0; i < 100; i++) {
            executor.execute(t);
        }
        executor.shutdown();
    }
}