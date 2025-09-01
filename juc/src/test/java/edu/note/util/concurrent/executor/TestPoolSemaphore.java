package edu.note.util.concurrent.executor;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class TestPoolSemaphore {
    public static void main(String[] args) {
        Pool pool = new Pool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection conn = pool.borrow();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                pool.free(conn);
            }).start();
        }
    }
}

