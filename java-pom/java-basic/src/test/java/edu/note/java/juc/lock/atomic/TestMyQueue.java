package edu.note.java.juc.lock.atomic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.juc.concurrent.atomic.MyQueueV1;
import edu.note.java.juc.concurrent.atomic.MyQueueV2;
import edu.note.java.util.Sleeper;

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

        MyQueueV2<String> queue = new edu.note.java.juc.concurrent.atomic.MyQueueV2<>();
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

