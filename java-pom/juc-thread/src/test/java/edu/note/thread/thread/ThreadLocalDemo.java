package edu.note.thread.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/11/29 下午2:58
 */
@Slf4j(topic = "c.thread.local")
public class ThreadLocalDemo {

    public static ThreadLocal<String> userName = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            Thread thread = Thread.currentThread();
            userName.set(thread.getName());
            log.info("thread={}, id={}", thread.getName(), userName.get());
        };
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
    }
}
