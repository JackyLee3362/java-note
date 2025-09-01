package edu.note.util.concurrent.thread;

import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * 在主线程中，如果其他线程都是守护线程，则主线程结束，守护线程也会结束
 * JVM 会在所有非守护线程执行完后退出，
 * t1 设置为守护线程后，即使线程未执行完，也会退出
 */
@Slf4j(topic = "c.TestDaemon")
public class TestDaemon {

    public static void main(String[] args) {
        log.debug("开始运行...");
        Thread t1 = new Thread(() -> {
            log.debug("开始运行...");
            Sleeper.sleep(5);
            log.debug("运行结束...");
        }, "daemon");
        // 设置该线程为守护线程
        // t1.setDaemon(true);
        t1.start();

        Sleeper.sleep(1);
        log.debug("运行结束...");
    }
}
