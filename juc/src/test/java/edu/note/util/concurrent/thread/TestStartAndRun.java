package edu.note.util.concurrent.thread;

import lombok.extern.slf4j.Slf4j;

// run 方法 和 start 方法区别
//  run 方法是 main 线程执行
//  start 是在线程中执行的
@Slf4j(topic = "c.Test4")
public class TestStartAndRun {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> log.debug("running..."), "t1");
        t1.start(); // t1 线程执行
        // t1.run(); // 在 main 线程执行
        log.debug("do other things...");
    }
}
