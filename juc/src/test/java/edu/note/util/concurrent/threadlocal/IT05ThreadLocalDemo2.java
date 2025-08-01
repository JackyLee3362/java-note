package edu.note.util.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IT05ThreadLocalDemo2 implements Runnable {

    // SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal
        .withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    @Test
    @DisplayName("ThreadLocal")
    public void test() throws InterruptedException {

        IT05ThreadLocalDemo2 obj = new IT05ThreadLocalDemo2();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter = "
            + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // formatter pattern is changed here by thread, but it won't reflect to other
        // threads
        formatter.set(new SimpleDateFormat());

        System.out.println(
            "Thread Name= " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }

}