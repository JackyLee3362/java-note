package edu.note.util.concurrent.guard;

import edu.note.thread.util.Downloader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "c.TestGuardedObjectV3")
public class TestGuardedObjectV3 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            GuardedObjectV3 v3 = Features.createFeature();

            new Thread(() -> {
                log.debug("等待 id({})...", v3.getId());
                log.debug("回应 id({}): [{}] 行", v3.getId(), ((List<String>) v3.get()).size());
            }).start();

            new Thread(() -> {
                try {
                    List<String> lines = Downloader.download();
                    log.debug("download complete id({})...", v3.getId());
                    Features.complete(v3.getId(), lines);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }).start();

        }
    }
}
