package edu.note.thread.util;

import java.util.concurrent.TimeUnit;

public class Sleeper {
    /**
     * @param second
     * @author: Jacky Lee
     * @date: 2024/4/1 15:32
     */
    public static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            // log.error(e.getMessage());
        }
    }

    public static void sleep(double i) {
        try {
            TimeUnit.MILLISECONDS.sleep((int) (i * 1000));
        } catch (InterruptedException e) {
            // log.error(e.getMessage());
        }
    }

    public static void randomSleep(int bound) {
        // TODO
    }
}
