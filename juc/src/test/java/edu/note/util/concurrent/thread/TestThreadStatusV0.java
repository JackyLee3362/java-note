package edu.note.util.concurrent.thread;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThreadStatusV0 {
    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable run = () -> {
            synchronized (obj) {
                try {
                    obj.wait();
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName() + " " + thread.getState());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        };

        new Thread(run, "t1").start();
        new Thread(run, "t2").start();

        TimeUnit.SECONDS.sleep(1);
        synchronized (obj) {
            System.out.println("notify begin ..."); // 断点
            obj.notifyAll();
        }
        System.out.println("notify end ..."); // 断点
    }
}
