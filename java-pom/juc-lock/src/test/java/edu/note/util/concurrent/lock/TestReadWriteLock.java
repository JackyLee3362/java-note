package edu.note.util.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.TestReadWriteLock")
public class TestReadWriteLock {

    @Test
    void test01() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        DataContainer container = new DataContainer();
        // pool.submit(new )
        // TODO
    }
}

