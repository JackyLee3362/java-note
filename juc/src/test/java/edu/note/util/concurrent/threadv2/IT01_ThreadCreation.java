package edu.note.util.concurrent.threadv2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/29 下午12:11
 */
@Slf4j(topic = "c.createThread")
public class IT01_ThreadCreation {

    @Test
    @DisplayName("继承 Thread 类")
    void test01() {
        Thread t = new Thread(() -> log.debug("继承 Thread 类实现 ..."), "threadDemo");
        t.start();
    }

    @Test
    @DisplayName("实现 Runnable 接口")
    void test02() {
        Runnable r = () -> log.debug("实现 Runnable 接口...");
        Thread t = new Thread(r, "runnableDemo");
        t.start();
    }

    @Test
    @DisplayName("实现 Callable 接口")
    void test03() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("running...");
            Thread.sleep(1000);
            return 100;
        });
        Thread t = new Thread(task, "futureTaskDemo");
        t.start();
        log.debug("{}", task.get());
    }

}
