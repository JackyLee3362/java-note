package edu.note.thread.executor;

import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/9/18 15:12
 */
@Slf4j(topic = "c.AddTask")
class MyTask1 extends RecursiveTask<Integer> {

    int n;

    public MyTask1(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + '}';
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            log.debug("join() {}", n);
            return n;
        }
        MyTask1 t1 = new MyTask1(n - 1);

        t1.fork();
        log.debug("fork() {} + {}", n, t1);
        int result = n + t1.join();
        log.debug("join() {} + {} = {}", n, t1, result);
        return result;
    }
}
