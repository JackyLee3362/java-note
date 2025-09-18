package edu.note.thread.executor;

import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/9/18 15:13
 */
@Slf4j(topic = "c.AddTask")
class AddTask2 extends RecursiveTask<Integer> {

    int begin;
    int end;

    public AddTask2(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }

    @Override
    protected Integer compute() {
        if (begin == end) {
            log.debug("join() {}", begin);
            return begin;
        }
        if (end - begin == 1) {
            log.debug("join() {} + {} = {}", begin, end, end + begin);
            return end + begin;
        }
        int mid = (end + begin) / 2;

        AddTask2 t1 = new AddTask2(begin, mid - 1);
        t1.fork();
        AddTask2 t2 = new AddTask2(mid + 1, end);
        t2.fork();
        log.debug("fork() {} + {} + {} = ?", mid, t1, t2);

        int result = mid + t1.join() + t2.join();
        log.debug("join() {} + {} + {} = {}", mid, t1, t2, result);
        return result;
    }
}
