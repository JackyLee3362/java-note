package edu.note.java.juc.guard;

import java.io.IOException;
import java.util.List;

import edu.note.java.util.concurrent.Downloader;
import edu.note.java.util.concurrent.guard.Features;
import edu.note.java.util.concurrent.guard.GuardedObjectV3;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
