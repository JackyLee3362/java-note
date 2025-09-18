package edu.note.thread.executor;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// https://www.cnblogs.com/heliusKing/p/12288226.html
@Slf4j(topic = "c.StarvationIssueTest")
public class StarvationIssueTest {

    static Random RANDOM = new Random();

    static Integer cooking() {
        return RANDOM.nextInt(10);
    }

    @Test
    @DisplayName("固定线程池会存在饥饿现象")
    void test01() throws InterruptedException {
        // 处理点餐 + 后厨
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(getRunnable(pool));
        pool.execute(getRunnable(pool));
    }

    @Test
    @DisplayName("饥饿现象 - 解决方法: 多个线程池")
    void test02() throws InterruptedException {

        // 处理点餐
        ExecutorService orderPool = Executors.newFixedThreadPool(1);
        // 处理后厨
        ExecutorService cookPool = Executors.newFixedThreadPool(1);

        orderPool.execute(getRunnable(cookPool));
        orderPool.execute(getRunnable(cookPool));
        orderPool.execute(getRunnable(cookPool));
    }

    private static Runnable getRunnable(ExecutorService cookPool) {
        return () -> {
            log.debug("启动线程...");
            Sleeper.sleep(0.2);
            Future<Integer> f = cookPool.submit(() -> {
                log.debug("获取随机数...");
                return cooking();
            });
            try {
                log.debug("获取结果: {}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        };
    }
}
