package edu.note.util.concurrent.lock;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSynchronizeDemo {

    /*
     * 需求：
     * 某电影院目前正在上映国产大片，共有100张票，而它有3个窗口卖票，请设计一个程序模拟该电影院卖票
     * 利用同步方法完成
     * 技巧：同步代码块
     */

    @Test
    @DisplayName("使用 synchronize 同步")
    public void test() throws InterruptedException {

        Runnable mr = new SellTicketsSynchronized();

        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);
        Thread t3 = new Thread(mr);

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