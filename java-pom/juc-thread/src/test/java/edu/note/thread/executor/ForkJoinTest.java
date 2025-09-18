package edu.note.thread.executor;

import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.ForkJoinTest")
public class ForkJoinTest {

    // ForkJoin 是一个分而治之的线程池，有点类似于 Hadoop 的 MapReduce
    // ForkJoin 可以分解计算密集型任务
    // https://juejin.cn/post/7268594193933172799
    // Task(5) + Task(4)  + Task(3) +  Task(2)  + Task(1)

    @Test
    void test01() {
        ForkJoinPool pool = new ForkJoinPool(4);
        Integer res = pool.invoke(new MyTask1(5));
        pool.shutdown();
        Assertions.assertEquals(15, res);
    }

    @Test
    void test02() {
        ForkJoinPool pool = new ForkJoinPool(4);
        Integer res = pool.invoke(new MyTask2(5, 10));
        pool.shutdown();
        Assertions.assertEquals(45, res);
    }

    @Test
    void test03(){
        ForkJoinPool pool = new ForkJoinPool(4);
        // System.out.println(pool.invoke(new MyTask1(5)));
        Integer res = pool.invoke(new MyTask3(5, 10));
        pool.shutdown();
        Assertions.assertEquals(45, res);

    }
}

