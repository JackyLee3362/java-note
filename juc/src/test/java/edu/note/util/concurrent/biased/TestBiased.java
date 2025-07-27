package edu.note.util.concurrent.biased;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

// -XX:-UseCompressedOops -XX:-UseCompressedClassPointers -XX:BiasedLockingStartupDelay=0 -XX:+PrintFlagsFinal
//-XX:-UseBiasedLocking tid=0x000000001f173000  -XX:BiasedLockingStartupDelay=0 -XX:+TraceBiasedLocking
@Slf4j(topic = "c.TestBiased")
public class TestBiased {
    static Thread t1, t2, t3;

    static class Dog {
    }

    // 测试偏向锁
    @Test
    void test1() {
        Dog d = new Dog();
        log.debug(ClassLayout.parseInstance(d).toPrintable());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        // 偏向锁默认是延迟的，所以需要等待一些时间后再启动
        // 也可以使用 VM 参数
        // -XX:BiasedLockingStartupDelay=0 来禁用延迟
        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());
    }

    // 测试加了 synchronized 后对象头信息
    // -XX:-UseBiasedLocking 禁用偏向锁
    @Test
    void test1_2() {
        Dog d = new Dog();
        // d.hashCode(); // 显示调用 hashCode 会禁用偏向锁
        log.debug(ClassLayout.parseInstance(d).toPrintable());
        synchronized (d) {
            log.debug(ClassLayout.parseInstance(d).toPrintable());
        }
        log.debug(ClassLayout.parseInstance(d).toPrintable());
    }

    // 测试撤销偏向锁
    @Test
    void test2() throws InterruptedException {
        // -XX:BiasedLockingStartupDelay=0
        Dog d = new Dog();

        Thread t1 = new Thread(() -> {
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            synchronized (TestBiased.class) {
                TestBiased.class.notify();
            }
        }, "t1");
        t1.start();


        Thread t2 = new Thread(() -> {
            synchronized (TestBiased.class) {
                try {
                    // 【类锁】等待
                    TestBiased.class.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
        }, "t2");
        t2.start();

        // 感觉可以把 t1 上移，然后把类锁删除
        t1.join();
        t2.join();
    }

    // 测试
    @Test
    void test3() throws InterruptedException {

        Vector<Dog> list = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1");
        t1.start();


        Thread t2 = new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            log.debug("===============> ");
            for (int i = 0; i < 30; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t2");
        t2.start();

        t1.join();
        t2.join();
    }


    /*
     * @description: 批量撤销
     * @author: Jacky Lee
     * @date: 2024/4/2 13:34
     */
    @Test
    void test4_c4_p37() throws InterruptedException {
        Vector<Dog> list = new Vector<>();

        // 视频里说的是 39 次后偏向锁会膨胀，但是我这边测不出来
        int loopNumber = 39;
        t1 = new Thread(() -> {
            for (int i = 0; i < loopNumber; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
            }
            LockSupport.unpark(t2);
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            LockSupport.park();
            log.debug("===============> ");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
            LockSupport.unpark(t3);
        }, "t2");
        t2.start();

        t3 = new Thread(() -> {
            LockSupport.park();
            log.debug("===============> ");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t3");
        t3.start();

        t3.join();
        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());
    }

    @Test
    void test5() throws InterruptedException {
        log.debug("begin");
        for (int i = 0; i < 6; i++) {
            Dog d = new Dog();
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            Thread.sleep(1000);
        }
    }


}


