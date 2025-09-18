package edu.note.thread.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

@Slf4j
public class ListThreadTest {


    /**
     * @description: 静态方法中，局部变量引用可能会造成不安全
     * @details: method1
     * @author: Jacky Lee
     * @date: 2024/4/1 15:52
     */
    @RepeatedTest(30)
    void testUnsafe1() throws InterruptedException {
        ListThreadUnsafe util = new ListThreadUnsafe();
        Thread t1 = new Thread(() -> {
            util.batch(200);
        });
        Thread t2 = new Thread(() -> {
            util.batch(200);
        });
        t1.start();
        t2.start();
        log.debug("list size is {}", util.list.size());
        Assertions.assertEquals(0, util.list.size());
        t1.join();
        t2.join();
    }

    @RepeatedTest(10)
    void testSafe() throws InterruptedException {
        // 由于是局部变量，所以线程安全
        ListThreadSafe util = new ListThreadSafe();
        Thread t1 = new Thread(() -> {
            util.batch(200);
        });
        Thread t2 = new Thread(() -> {
            util.batch(200);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @RepeatedTest(10)
    void testSafeSub() throws InterruptedException {
        ListThreadSafeSubClass util = new ListThreadSafeSubClass();
        Thread t1 = new Thread(() -> {
            util.batch(200);
        });
        Thread t2 = new Thread(() -> {
            util.batch(200);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

