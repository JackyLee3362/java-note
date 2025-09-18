package edu.note.thread.executor;

import edu.note.thread.Pool;
import java.sql.Connection;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPoolWithSqlConnection {
    @Test
    @DisplayName("测试连接池")
    void test() {

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
