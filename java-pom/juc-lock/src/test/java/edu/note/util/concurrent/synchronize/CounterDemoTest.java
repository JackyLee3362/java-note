package edu.note.util.concurrent.synchronize;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.CounterDemoTest")
public class CounterDemoTest {

    static int counter = 0;
    static final Object lock = new Object();

    @Test
    void test01() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }


    @Test
    void test02() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }

    @Test
    void test03() throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                room.decrement();
            }
        }, "t2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.debug("count: {}", room.get());
    }

    static class Room {

        int value = 0;

        public void increment() {
            synchronized (this) {
                value++;
            }
        }

        public void decrement() {
            synchronized (this) {
                value--;
            }
        }

        public int get() {
            synchronized (this) {
                return value;
            }
        }
    }
}
