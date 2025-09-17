package A05_多线程.d31_多线程.a15_课堂练习.test2_送礼品;

import A05_多线程.d31_多线程.a15_课堂练习.test1_卖电影票.MyThread;

public class MyRunable implements Runnable {

    // 第二种方式实现多线程，测试类中MyRunable只创建一次，所以不需要加static
    int count = 100;

    @Override
    public void run() {
        // 1.循环
        while (true) {
            // 2.同步代码块
            synchronized (MyThread.class) {
                // 3.判断共享数据（已经到末尾）
                if (count < 10) {
                    System.out.println("礼物还剩下" + count + "不再赠送");
                    break;
                } else {
                    // 4.判断共享数据（没有到末尾）
                    count--;
                    System.out.println(Thread.currentThread().getName() + "在赠送礼物，还剩下" + count + "个礼物!!!");
                }
            }
        }
    }
}
