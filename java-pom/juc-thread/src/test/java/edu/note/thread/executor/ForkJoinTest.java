package edu.note.thread.executor;

import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.ForkJoinTest")
public class ForkJoinTest {

    // ForkJoin 是一个分而治之的线程池，有点类似于 Hadoop 的 MapReduce
    // ForkJoin 可以分解计算密集型任务
    // https://juejin.cn/post/7268594193933172799

    @Test
    void test01() {
        ForkJoinPool pool = new ForkJoinPool(4);
        Integer res = pool.invoke(new MyTask1(5));
        log.info("结果是: {}", res);
        // new MyTask1(5) 5+ new MyTask1(4) 4 + new MyTask1(3) 3 + new MyTask1(2) 2 + new MyTask1(1)
        pool.shutdown();
    }

    @Test
    void test02() {
        ForkJoinPool pool = new ForkJoinPool(4);
        Integer res = pool.invoke(new MyTask2(5, 10));
        log.info("结果是: {}", res);
        // new MyTask1(5) 5+ new MyTask1(4) 4 + new MyTask1(3) 3 + new MyTask1(2) 2 + new MyTask1(1)
        pool.shutdown();
    }

    @Test
    void test03(){

        ForkJoinPool pool = new ForkJoinPool(4);
        // System.out.println(pool.invoke(new MyTask1(5)));
        System.out.println(pool.invoke(new MyTask3(1, 5)));
        pool.shutdown();

    }
}

