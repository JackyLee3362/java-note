package edu.note.util.concurrent.lock;

import edu.note.util.concurrent.example.SellTicketsReentrantLock;
import org.junit.jupiter.api.Test;

public class TestReentrantLockDemo {

    /*
     * 需求：
     * 某电影院目前正在上映国产大片，共有100张票，而它有3个窗口卖票，请设计一个程序模拟该电影院卖票
     * 用JDK5的lock实现
     */
    @Test
    void test() throws InterruptedException {
        Runnable rm = new SellTicketsReentrantLock();
        Thread t1 = new Thread(rm);
        Thread t2 = new Thread(rm);
        Thread t3 = new Thread(rm);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}