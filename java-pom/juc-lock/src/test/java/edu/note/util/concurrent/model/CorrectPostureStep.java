package edu.note.util.concurrent.model;

import lombok.extern.slf4j.Slf4j;

import static edu.note.thread.util.Sleeper.sleep;

// 同步模型
@Slf4j(topic = "c.TestCorrectPosture")
public class CorrectPostureStep {
    static final Object room = new Object();
    static boolean condition1 = false;
    static boolean condition2 = false;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (room) {
                while (!condition1) {
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                if (condition1) {
                    log.debug("可以开始干活了");
                } else {
                    log.debug("没干成活...");
                }
            }
        }, "T1").start();

        new Thread(() -> {
            synchronized (room) {
                while (!condition2) {
                    log.debug("没外卖，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                if (condition2) {
                    log.debug("可以开始干活了");
                } else {
                    log.debug("没干成活...");
                }
            }
        }, "小女").start();

        sleep(1);
        new Thread(() -> {
            synchronized (room) {
                condition2 = true;
                log.debug("外卖到了噢！");
                room.notifyAll();
            }
        }, "送外卖的").start();

    }

}
