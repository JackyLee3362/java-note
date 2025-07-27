package edu.note.util.concurrent.synchronize;

import lombok.extern.slf4j.Slf4j;

// PrintConcurrentLocks
@Slf4j
public class TestSynchronized02 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (TestSynchronized02.class) {
                try {
                    System.out.println("Thread 1: Start");
                    Thread.sleep(5000);
                    System.out.println("Thread 1: End");
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        },"t1").start();

        System.out.println("Thread Main start Sleep");
        Thread.sleep(3000);
        System.out.println("Thread Main end Sleep");
        new Thread(()->{
            System.out.println("Thread 2 Start");
            synchronized (TestSynchronized02.class) {
                System.out.println("Thread 2");
            }
        },"t2").start();

        new Thread(()->{
            System.out.println("Thread 3 Start");
            synchronized (TestSynchronized02.class) {
                System.out.println("Thread 3");
            }
        },"t3").start();
    }
}
