package edu.note.java.juc.thread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStartAndRunTest {

    Thread t = new Thread(() -> {
        log.info("running...");
    });

    @Test
    @DisplayName("run() 在主线程中执行")
    void testRun() {
        log.info("主线程运行");
        t.run();
    }

    @Test
    @DisplayName("start() 在线程中执行")
    void testStart() {
        log.info("主线程运行");
        t.start();
    }

}
