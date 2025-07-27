package edu.note.util.concurrent.mythreadpool;

import edu.note.util.concurrent.util.Sleeper;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/18 00:03
 */
@Slf4j(topic = "c.test-thread-pool")
public class TestMyThreadPool {


    public static void main(String[] args) {

        MyThreadPool myThreadPool = new MyThreadPool(2, 1, TimeUnit.SECONDS, 10);
        for (int i = 0; i < 15; i++) {
            int j = i;
            myThreadPool.execute(() -> {
                Sleeper.sleep(1);
                log.debug("{}", j);
            });

        }


    }

}
