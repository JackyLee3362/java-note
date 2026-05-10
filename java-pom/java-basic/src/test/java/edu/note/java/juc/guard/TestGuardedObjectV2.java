package edu.note.java.juc.guard;


import java.util.Arrays;
import java.util.List;

import edu.note.java.util.Sleeper;
import edu.note.java.util.concurrent.guard.GuardedObjectV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestGuardedObjectV2 {
    public static void main(String[] args) {
        GuardedObjectV2 v2 = new GuardedObjectV2();
        new Thread(() -> {
            Sleeper.sleep(1);
            v2.complete(null);
            Sleeper.sleep(1);
            v2.complete(Arrays.asList("a", "b", "c"));
        }).start();

        Object response = v2.get(2500);
        if (response != null) {
            log.debug("get response: [{}] lines", ((List<String>) response).size());
        } else {
            log.debug("can't get response");
        }
    }
}
