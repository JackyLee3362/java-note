package edu.note.util.concurrent.lock;


import edu.note.thread.util.Sleeper;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.CyclicBarrierTest")
public class CyclicBarrierTest {

    @Test
    void test01() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> log.debug("task1, task2 finish..."));
        for (int i = 0; i < 3; i++) { // task1 task2 task1
            pool.submit(() -> {
                log.debug("task1 begin...");
                Sleeper.sleep(1);
                try {
                    barrier.await(); // 2-1=1
                    log.info("task1 continue");
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error(e.getMessage());
                }
            });
            pool.submit(() -> {
                log.debug("task2 begin...");
                Sleeper.sleep(2);
                try {
                    barrier.await(); // 1-1=0
                    log.info("task2 continue");
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error(e.getMessage());
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
    }
}
