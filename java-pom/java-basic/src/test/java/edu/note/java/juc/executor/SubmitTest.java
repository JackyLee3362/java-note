package edu.note.java.juc.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import edu.note.java.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubmitTest {

    static final Callable<Integer> task1 = () -> {
        log.debug("begin 1");
        Sleeper.sleep(1);
        log.debug("end 1");
        return 1;
    };
    static final Callable<Integer> task2 = () -> {
        log.debug("begin 2");
        Sleeper.sleep(2);
        log.debug("end 2");
        return 2;
    };
    static final Callable<Integer> task3 = () -> {
        log.debug("begin 3");
        Sleeper.sleep(3);
        log.debug("end 3");
        return 3;
    };

    @Test
    void testMethod1() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Integer> future = pool.submit(task1);
        log.debug("{}", future.get());
    }

    @Test
    void testMethod2() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        List<Future<Integer>> futures = pool.invokeAll(Arrays.asList(task1, task2, task3));

        futures.forEach(f -> {
            try {
                log.debug("{}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        });
    }

    @Test
    void testMethod3() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Integer result = pool.invokeAny(Arrays.asList(task1, task2, task3));
        log.debug("{}", result);
    }
}
