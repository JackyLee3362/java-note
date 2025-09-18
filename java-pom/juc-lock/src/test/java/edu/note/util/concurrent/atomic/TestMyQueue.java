package edu.note.util.concurrent.atomic;

import edu.note.thread.util.Sleeper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestMyQueue {

    @Test
    @DisplayName("MyQueue1")
    void test(){

        MyQueueV1<String> queue = new MyQueueV1<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        System.out.println(queue);
    }
    @Test
    @DisplayName("MyQueue2")
    void test02(){

        MyQueueV2<String> queue = new edu.note.util.concurrent.atomic.MyQueueV2<>();
        new Thread(() -> {
            queue.offer("1");
        }, "t1").start();
        new Thread(() -> {
            queue.offer("2");
        }, "t2").start();
        Sleeper.sleep(1);
        System.out.println(queue);
    }
}

