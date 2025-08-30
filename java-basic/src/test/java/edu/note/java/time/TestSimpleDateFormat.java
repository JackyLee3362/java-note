package edu.note.java.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestSimpleDateFormat {

    @Test
    void test01() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Assertions.assertEquals(0, sdf.parse("1951-04-21"));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }).start();
        }
    }

    @Test
    void test02() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (sdf) {
                    try {
                        Assertions.assertEquals(0, sdf.parse("1951-04-21"));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }).start();
        }
    }

    @Test
    void test03() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LocalDate date = dtf.parse("2018-10-01", LocalDate::from);
                Assertions.assertEquals(0, date);
            }).start();
        }
    }
}
