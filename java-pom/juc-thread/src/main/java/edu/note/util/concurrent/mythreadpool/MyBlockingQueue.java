package edu.note.util.concurrent.mythreadpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/17 23:48
 */

@Slf4j(topic = "c.blocking-queue")
class MyBlockingQueue<T> {

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 最大任务数
    private int capacity;


    // 阻塞队列
    private Deque<T> deque = new ArrayDeque<>();
    // 锁
    private ReentrantLock lock = new ReentrantLock();

    // 条件1
    private Condition emptyWaitSet = lock.newCondition();

    // 条件2
    private Condition fullWaitSet = lock.newCondition();

    // 获取
    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    // 超时等待
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (deque.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }

    }

    public void put(T element) {
        lock.lock();
        try {
            while (size() == capacity) {
                try {
                    log.debug("等待加入任务队列 {}", element);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public void offer(T element, long timeout, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (size() == capacity) {
                try {
                    log.debug("等待加入任务队列 {}", element);
                    nanos = fullWaitSet.awaitNanos(nanos);
                    fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }


}