package edu.note.util.concurrent.threadv2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/2 00:20
 */

@Slf4j(topic = "c.startVsRun")
public class IT02_StartRun {

    Thread t = new Thread(() -> {
        log.debug("running...");
    });

    @Test
    public void testStart() {
        t.start();
        log.debug("主线程");
    }

    @Test
    public void testRun() {
        log.debug("主线程");
        t.run();
        log.debug("主线程 在做其他事情 ... ");
    }
}
