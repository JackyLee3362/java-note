package edu.note.util.concurrent.misc;

// TestHowToDebugThread
public class TestHowToDebugThread {
    public static void main(String[] args) {
        Runnable r = TestHowToDebugThread::f1;
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        t1.start();
        t2.start();
        t1.start();

        f1();

    }

    public static void f1() {
        int x = 1;
        int y = 2;
        int m = f2_add(x, y);
    }

    private static int f2_add(int x, int y) {
        return x + y;
    }
}
