package edu.note.util.concurrent.synchronize;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.demo")
public class SynchronizedDemo {

    static final Object lock = new Object();

    // t1 获取锁进入 synchronized 块并 lock.wait()
    // t2 获取锁进入 synchronized 块并 lock.wait()
    // main notify all
    //
    public static void main(String[] args) throws InterruptedException {

        Runnable run = () -> {
            synchronized (lock) {
                try {
                    Thread thread = Thread.currentThread();
                    log.info("{} get lock, state={}", thread.getName(), thread.getState());
                    lock.wait();
                    log.info("{}, state={}", thread.getName(), thread.getState());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        };

        new Thread(run).start();
        new Thread(run).start();

        TimeUnit.SECONDS.sleep(1);
        synchronized (lock) {
            log.info("notify all");
            lock.notifyAll();
        }
        log.info("notify end");
    }
}
