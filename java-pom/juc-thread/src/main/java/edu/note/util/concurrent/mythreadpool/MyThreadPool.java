package edu.note.util.concurrent.mythreadpool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/17 23:20
 */

@Slf4j(topic = "c.thread-poll")
public class MyThreadPool {

    private MyBlockingQueue<Runnable> taskQueue;

    private HashSet<Worker> workers = new HashSet<>();

    // 核心线程数
    private int coreSize;

    // 超时时间
    private long timeout;

    // 时间单位
    private TimeUnit unit;

    public MyThreadPool(int coreSize, long timeout, TimeUnit unit, int capacity) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.taskQueue = new MyBlockingQueue<>(capacity);
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增 worker:{}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入任务队列:{}", task);
                taskQueue.put(task);
            }
        }
    }

    public class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 如果队列不为空
            // while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(timeout, unit)) != null) {
                try {
                    log.debug("执行任务:{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            // 移除 Work
            synchronized (workers) {
                log.debug("移除worker:{}", this);
                workers.remove(this);
            }

        }
    }


}
