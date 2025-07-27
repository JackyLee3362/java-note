package edu.note.util.concurrent.observe;

import edu.note.util.concurrent.util.FileReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @description: 解释同步和异步的区别，day01-02.004 00:03:31
 * @author: Jacky Lee
 * @date: 2024/3/30 10:41
 */
@Slf4j(topic = "c.TestSyncAndAsync")
public class TestSyncAndAsync {

    @Test
    public void testSync() {
        FileReader.pseudoRead("");
        log.debug("sync: do other things ...");
    }
    @Test
    public void testAsync() {
        new Thread(() -> FileReader.pseudoRead("")).start();
        log.debug("Async: do other things ...");
    }


}
