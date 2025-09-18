package edu.note.util.concurrent.lock;


import edu.note.thread.util.Sleeper;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.CountDownLatchTest")
public class CountDownLatchTest {
    // public static void main(String[] args) throws InterruptedException, ExecutionException {
    //     test3();
    // }

    @Test
    void test01() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            int x = j;
            log.info("开始加载...");
            pool.submit(() -> {
                for (int i = 0; i <= 10; i++) {
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                    log.info("{}, ({}%)", Thread.currentThread().getName(), i * 10);
                }
                latch.countDown();
            });
        }
        latch.await();
        log.info("加载完成...");
        pool.shutdown();
    }

    @Test
    void test02() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(() -> {
            log.debug("前置任务 1: begin...");
            Sleeper.sleep(1);
            log.debug("前置任务 1: end...{}", latch.getCount());
            latch.countDown();
        });
        pool.submit(() -> {
            log.debug("前置任务 2: begin...");
            Sleeper.sleep(1.5);
            log.debug("前置任务 2: end...{}", latch.getCount());
            latch.countDown();
        });
        pool.submit(() -> {
            log.debug("前置任务 3: begin...");
            Sleeper.sleep(2);
            log.debug("前置任务 3: end...{}", latch.getCount());
            latch.countDown();
        });
        pool.submit(() -> {
            try {
                log.debug("后置任务: waiting...");
                latch.await();
                log.debug("后置任务: wait end...");
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        });
        pool.shutdown();
        pool.awaitTermination(4, TimeUnit.MINUTES);
    }


}
