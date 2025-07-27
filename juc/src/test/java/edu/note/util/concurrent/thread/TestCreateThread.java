package edu.note.util.concurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @description: 如何创建线程
 * @link: day01-03.001 创建线程
 * @return: null
 * @author: Jacky Lee
 * @date: 2024/3/30 11:22
 */
@Slf4j
public class TestCreateThread {

    @Test
    @DisplayName("使用 Thread 类创建")
    public void testCreate01() {
        // 创建时设置线程名
        Thread t = new Thread(() -> log.debug("thread class: running..."), "t2");
        // 之后也可以修改
        t.setName("t1");
        t.start();
    }

    @Test
    @DisplayName("使用 Runnable 接口创建")
    public void testCreate02() {
        Runnable r = () -> log.debug("runnable interface: running");
        Thread t = new Thread(r, "t2");
        t.start();
    }

    @Test
    @DisplayName("使用 FutureTask 创建")
    public void testCreate03() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("using future task: running...");
            Thread.sleep(1000);
            return 101;
        });
        Thread t = new Thread(task, "futureTaskDemo");
        t.start();

        log.debug("{}", task.get());
    }
}
