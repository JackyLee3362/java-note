package edu.note.util.concurrent.lock;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Semaphore")
public class SemaphoreDemo {

    // 请求的数量
    private static final int num = 550;

    public static void main(String[] args) throws InterruptedException {

    // }
    // @Test
    // void test01() throws InterruptedException {


        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService pool = Executors.newFixedThreadPool(300);
        // 初始许可证数量
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < num; i++) {
            final int threadbare = i;
            pool.execute(() -> {// Lambda 表达式的运用
                try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                    test(threadbare);
                    semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            });
        }
        pool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Sleeper.sleepRandom(1000); // 模拟请求的耗时操作
        log.debug("线程 {} 开始执行", threadnum);
        Sleeper.sleepRandom(1000);
    }
}
