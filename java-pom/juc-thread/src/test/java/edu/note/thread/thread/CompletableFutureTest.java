package edu.note.thread.thread;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/18 13:03
 */
@Slf4j(topic = "c.CompletableFuture")
public class CompletableFutureTest {

    @Test
    void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.completedFuture(100);
        Assertions.assertEquals(100, future.get());
    }

    @Test
    void test02() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            log.info("当前线程：{}", Thread.currentThread().getId());
            return 100;
        });
        Assertions.assertEquals(100, future.get());
    }

    // @Test
    @RepeatedTest(10)
    void test03() throws ExecutionException, InterruptedException {
        Random random = new Random();
        CompletableFuture<Integer> taskFailed = CompletableFuture.supplyAsync(() -> {
            int num = random.nextInt(10);
            if (num < 5) {
                return num;
            }
            throw new RuntimeException("task failed");
            // throw new RuntimeException("task failed");
        }).thenApply(r -> r * 10).exceptionally((e) -> 0);

        Assertions.assertEquals(0, taskFailed.get());
    }

    @Test
    @DisplayName("")
    void test0() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1);
        future.thenRunAsync(() -> {
            log.info(Thread.currentThread().getName());
            log.info("Done");
        });
        future.thenAcceptAsync(result -> {
            log.info(Thread.currentThread().getName());
            log.info("{}", result);
        });
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
