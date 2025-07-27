package edu.note.util.concurrent.deadlock;


// FROM java-concurrent-questions-01#什么是线程死锁？如何避免死锁？
public class DeadLockDemo {
    public static final Object resource1 = new Object();
    public static final Object resource2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized(resource1){
                System.out.println("已经获取到资源 1🔴");
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("等待获取到资源 2🟡...");
                synchronized(resource2){
                    System.out.println("已经获取到资源 2🟡");
                }
            }
        },"线程1").start();

        new Thread(()->{
            synchronized(resource2){
                System.out.println("已经获取到资源 2🟡");
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("等待获取到资源 1🔴...");
                synchronized(resource1){
                    System.out.println("已经获取到资源 1🔴");
                }
            }
        }).start();
    }

}
