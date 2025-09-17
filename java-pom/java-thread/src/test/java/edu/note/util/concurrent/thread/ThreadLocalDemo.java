package edu.note.util.concurrent.thread;

/**
 * @author jackylee
 * @date 2024/11/29 下午2:58
 */
public class ThreadLocalDemo {

    public static ThreadLocal<Long> userId = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            userId.set(10L);
            System.out.println(Thread.currentThread().getName() + " " + userId.get());
        }, "线程1");
        Thread t2 = new Thread(() -> {
            userId.set(20L);
            System.out.println(Thread.currentThread().getName() + " " + userId.get());
        }, "线程2");
        t1.start();
        t2.start();
    }
}
