package A05_多线程.d31_多线程.a15_课堂练习.test3_打印奇数;

import A05_多线程.d31_多线程.a15_课堂练习.test1_卖电影票.MyThread;

public class MyRunable implements Runnable {

    // 第二种方式实现多线程，测试类中MyRunable只创建一次，所以不需要加static
    int number = 1;

    @Override
    public void run() {
        // 1.循环
        while (true) {
            // 2.同步代码块
            synchronized (MyThread.class) {
                // 3.判断共享数据（已经到末尾）
                if (number > 100) {
                    break;
                } else {
                    // 4.判断共享数据（没有到末尾）
                    if (number % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + "打印数字" + number);
                    }
                    number++;
                }
            }
        }
    }
}
