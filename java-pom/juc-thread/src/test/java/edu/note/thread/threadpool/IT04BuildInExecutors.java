package edu.note.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// FROM JavaGuide\docs\java\concurrent\java-concurrent-questions-03.md#如何创建线程池？
// 内置的Executors创建
public class IT04BuildInExecutors {
    public static void main(String[] args) {
        // 不建议使用

        // 该方法返回一个固定线程数量的线程池。
        // 该线程池中的线程数量始终不变。当有一个新的任务提交时，线程池中若有空闲线程，则立即执行
        // 若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务
        ExecutorService executor1 = Executors.newFixedThreadPool(5);

        // 该方法返回一个只有一个线程的线程池。
        // 若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，待线程空闲，按先入先出的顺序执行队列中的任务。
        ExecutorService executor2 = Executors.newSingleThreadExecutor();

        // 该方法返回一个可根据实际情况调整线程数量的线程池。初始大小为0。
        // 当有新任务提交时，如果当前线程池中没有线程可用，它会创建一个新的线程来处理该任务。
        // 如果在一段时间内（默认为 60 秒）没有新任务提交，核心线程会超时并被销毁，从而缩小线程池的大小。
        ExecutorService executor3 = Executors.newCachedThreadPool();

        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ExecutorService executor4 = Executors.newSingleThreadScheduledExecutor();

    }
}
