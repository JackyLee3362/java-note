package edu.note.thread.executor;

import java.util.concurrent.ForkJoinPool;

public class TestForkJoin {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        // System.out.println(pool.invoke(new AddTask1(5)));
        System.out.println(pool.invoke(new AddTask3(1, 5)));
    }
}

