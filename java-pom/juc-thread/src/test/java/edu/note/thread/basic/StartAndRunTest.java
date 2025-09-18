package edu.note.thread.basic;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

// run 方法 和 start 方法区别
//  run 方法是 main 线程执行
//  start 是在线程中执行的
@Slf4j(topic = "c.start.and.run")
public class StartAndRunTest {

    Thread t = new Thread(() -> {
        log.debug("running...");
    });

    @Test
    void testStart() {
        t.start();
        log.debug("主线程");
    }

    @Test
    void testRun() {
        log.debug("主线程");
        t.run();
        log.debug("主线程 在做其他事情 ... ");
    }
}
