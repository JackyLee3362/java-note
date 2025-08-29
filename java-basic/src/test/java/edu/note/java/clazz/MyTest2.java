package edu.note.java.clazz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyTest2 {
    public static int cnt = 1;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        // Thread t = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         synchronized(this){
        //             String name = Thread.currentThread().getName();
        //             System.out.println(name + ":" + cnt);
        //             cnt += 1;
        //         }
        //     }
        // });
        // for (int i = 0; i < 100; i++) {
        //     executor.execute(t);
        // }
        for (int i = 0; i < 10; i++) {
            int temp = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + ":" + temp);
                }
            });
        }
        executor.shutdown();
    }

}
