package edu.note.thread.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sleeper {

    final static Random random = new Random();


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

    /**
     * bound 是毫秒上限
     *
     * @param bound
     */
    public static void sleepRandom(int bound) {
        try {
            int i = random.nextInt(bound);
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            // ...
        }


    }
}
