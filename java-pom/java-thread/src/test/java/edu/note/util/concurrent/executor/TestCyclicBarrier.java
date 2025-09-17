package edu.note.util.concurrent.executor;


import edu.note.util.concurrent.executor.util.Sleeper;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestCyclicBarrier")
public class TestCyclicBarrier {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            log.debug("task1, task2 finish...");
        });
        for (int i = 0; i < 3; i++) { // task1 task2 task1
            service.submit(() -> {
                log.debug("task1 begin...");
                Sleeper.sleep(1);
                try {
                    barrier.await(); // 2-1=1
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error(e.getMessage());
                }
            });
            service.submit(() -> {
                log.debug("task2 begin...");
                Sleeper.sleep(2);
                try {
                    barrier.await(); // 1-1=0
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error(e.getMessage());
                }
            });
        }
        service.shutdown();

    }

    private static void test1() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 3; i++) {
            CountDownLatch latch = new CountDownLatch(2);
            service.submit(() -> {
                log.debug("task1 start...");
                Sleeper.sleep(1);
                latch.countDown();
            });
            service.submit(() -> {
                log.debug("task2 start...");
                Sleeper.sleep(2);
                latch.countDown();
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.debug("task1 task2 finish...");
        }
        service.shutdown();
    }
}
