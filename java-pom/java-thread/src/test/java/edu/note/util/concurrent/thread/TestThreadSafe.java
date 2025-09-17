package edu.note.util.concurrent.thread;

import java.util.ArrayList;

public class TestThreadSafe {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        testUnsafe1();
    }

    /**
     * @description: 静态方法中，局部变量引用可能会造成不安全
     * @details: method1
     * @author: Jacky Lee
     * @date: 2024/4/1 15:52
     */
    public static void testUnsafe1() {
        ThreadUnsafe test = new ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.func(LOOP_NUMBER);
            }, "Thread" + (i + 1)).start();
        }

    }

    public static void testUnsafe() {
        ThreadSafeSubClass test = new ThreadSafeSubClass();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.func(LOOP_NUMBER);
            }, "Thread" + (i + 1)).start();
        }
    }
}

/**
 * @description: list 是全局变量
 * @author: Jacky Lee
 * @date: 2024/4/1 16:03
 */
class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<>();

    public void func(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            listAdd();
            listRemove();
        }
    }

    private void listAdd() {
        list.add("1");
    }

    private void listRemove() {
        list.remove(0);
    }
}

/**
 * @description: 把 list 变成局部变量
 * @author: Jacky Lee
 * @date: 2024/4/1 16:02
 */
class ThreadSafe {
    public final void func(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            listAdd(list);
            listRemove(list);
        }
    }

    public void listAdd(ArrayList<String> list) {
        list.add("1");
    }

    // 方法是 private 可以限制子类Override，从而达到线程安全
    private void listRemove(ArrayList<String> list) {
        System.out.println(1);
        list.remove(0);
    }
}

class ThreadSafeSubClass extends ThreadSafe {
    public void listRemove(ArrayList<String> list) {
        System.out.println(2);
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}