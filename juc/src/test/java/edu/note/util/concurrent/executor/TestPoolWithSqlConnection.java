package edu.note.util.concurrent.executor;


import java.sql.Connection;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPoolWithSqlConnection {
    public static void main(String[] args) {
        Pool pool = new Pool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection conn = pool.borrow();
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                pool.free(conn);
            }).start();
        }
    }
}

