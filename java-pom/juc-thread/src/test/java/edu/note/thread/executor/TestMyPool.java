package edu.note.thread.executor;

import edu.note.thread.MyPool;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class TestMyPool {
    public static void main(String[] args) {
        MyPool myPool = new MyPool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection conn = myPool.borrow();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                myPool.free(conn);
            }).start();
        }
    }
}

