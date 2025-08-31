package edu.note.util.concurrent.guard;

import static edu.note.util.concurrent.util.Sleeper.sleep;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestGuardedObjectV2")
public class TestGuardedObjectV2 {
    public static void main(String[] args) {
        GuardedObjectV2 v2 = new GuardedObjectV2();
        new Thread(() -> {
            sleep(1);
            v2.complete(null);
            sleep(1);
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
