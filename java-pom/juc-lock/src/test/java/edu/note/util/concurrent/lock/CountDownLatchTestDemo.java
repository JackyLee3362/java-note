package edu.note.util.concurrent.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class CountDownLatchTestDemo {

    // 处理文件的数量
    private static final int threadCount = 15;

    @Test
    void test() throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（推荐使用构造方法创建）
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            // final int threadNum = i;
            Random random = new Random();
            threadPool.execute(() -> {
                try {
                    // 处理文件的业务操作
                    int second = random.nextInt(6);
                    String name = Thread.currentThread().getName();
                    System.out.println(name + ": 处理文件需要 " + second + " 秒");
                    Thread.sleep(second * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 表示一个文件已经被完成
                    countDownLatch.countDown();
                }

            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("文件处理完毕");
    }
}