package edu.note.thread.schedule;

import edu.note.thread.util.Sleeper;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/18 15:44
 */
@Slf4j(topic = "c.TestSchedule")
public class TestSchedule {

    @Test
    @DisplayName("Fix定时任务")
    void test05() {
        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleAtFixedRate(() -> log.info("执行定时任务"), 0, 500, TimeUnit.MILLISECONDS);
        Sleeper.sleep(3);
    }

    @Test
    @DisplayName("定时任务 throw")
    void test06() {
        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        Random random = new Random();
        pool.scheduleAtFixedRate(() -> {
            log.info("执行定时任务...");
            int i = random.nextInt(10);
            log.info("获取随机数 {} ...", i);
            if (i < 5) {
                return;
            }
            throw new RuntimeException();
        }, 0, 500, TimeUnit.MILLISECONDS);
        // 生成随机数会造成阻塞
        Sleeper.sleep(10);
    }

    @Test
    @DisplayName("定时任务 schedule 执行一次")
    void test07() {
        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        Random random = new Random();
        pool.schedule(() -> log.info("schedule 执行一次"), 0, TimeUnit.MILLISECONDS);
        // 生成随机数会造成阻塞
        Sleeper.sleep(10);
    }

    @Test
    @DisplayName("定时任务执行时间过长")
    void test08() {
        // 该方法返回一个用来在给定的延迟后运行任务或者定期执行任务的线程池。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        Random random = new Random();
        pool.scheduleAtFixedRate(() -> {
            log.info("执行超长时间定时任务...");
            Sleeper.sleep(2);
            log.info("结束...");
        }, 0, 500, TimeUnit.MILLISECONDS);
        // 生成随机数会造成阻塞
        Sleeper.sleep(10);
    }
}
