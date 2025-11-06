package edu.note.util.concurrent.reentrant;

import static edu.note.thread.util.Sleeper.sleep;

import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * lock.lock(): 不响应中断，线程会直到获得锁。
 * lock.lockInterruptibly(): 响应中断，能在锁等待期间被 interrupt 线程安全地打断。
 *
 *
 * @author jackylee
 * @date 2025-11-06 10:30
 */
@Slf4j(topic = "c.InterruptDemo")
public class ReentrantInterruptDemo {

    /**
     * lock.lock()
     * 会一直等待直到获取锁, 不响应线程的中断(interupt)信号
     * 如果被其他线程 interupt() 只设置中断标志位，不会抛出异常，也不会提前结束锁等待
     * 适合于不需中断，不可取消的任务
     *
     * lock.lockInterruptibly()
     * 线程调用后，如果锁不可用，会等待获得锁
     * 可以响应线程在锁等待期间收到的中断信号（interrupt）。
     * 如果线程被 interrupt()，会立刻抛出 InterruptedException 并结束等待。
     * 适合于需要可取消、可中断的任务（如有超时、中断需求的场景）。
     **/
    public static void main(String[] args) {

        // 默认是非公平锁
        ReentrantLock lock = new ReentrantLock();
        // ReentrantLock lock = new ReentrantLock(true);

        Thread t1 = new Thread(() -> {
            try {
                log.debug("尝试获取锁...");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                log.debug("等锁的过程中被打断");
                return;
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "thr1");

        lock.lock();
        log.debug("主线程获得了锁");
        t1.start();

        try {
            sleep(1);
            t1.interrupt();
            log.debug("主线程执行打断");
        } finally {
            lock.unlock();
        }
    }
}
