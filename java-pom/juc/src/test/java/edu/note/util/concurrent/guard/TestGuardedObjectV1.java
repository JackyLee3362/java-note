package edu.note.util.concurrent.guard;

import static edu.note.thread.util.Downloader.download;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "c.TestGuardedObject")
public class TestGuardedObjectV1 {
    public static void main(String[] args) {
        GuardedObjectV1 guardedObjectV1 = new GuardedObjectV1();
        new Thread(() -> {
            try {
                List<String> response = download();
                log.debug("下载完成");
                guardedObjectV1.complete(response);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }, "t1").start();

        log.debug("等待中...");
        Object response = guardedObjectV1.get();
        log.debug("结果是: [{}] 行", ((List<String>) response).size());

    }
}
