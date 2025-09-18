package edu.note.util.concurrent.lock;

import edu.note.thread.util.Sleeper;
import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.SemaphoreTest")
public class SemaphoreTest {


    @Test
    void test01() {

        // 1. 创建 semaphore 对象
        Semaphore semaphore = new Semaphore(3);

        // 2. 10个线程同时运行
        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                try {
                    log.debug("enter...");
                    semaphore.acquire();
                    log.info("acquire...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    log.debug("running...");
                    Sleeper.sleep(.5);
                    log.debug("end...");
                } finally {
                    log.info("release...");
                    semaphore.release();
                }
            }).start();
        }
        Sleeper.sleep(5);
    }
}
