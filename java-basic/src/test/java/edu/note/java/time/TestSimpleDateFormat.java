package edu.note.java.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class TestSimpleDateFormat {

    @Test
    public void test01() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(sdf.parse("1951-04-21"));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }).start();
        }
    }

    @Test
    public void test02() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (sdf) {
                    try {
                        System.out.println(sdf.parse("1951-04-21"));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }).start();
        }
    }

    @Test
    public void test03() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LocalDate date = dtf.parse("2018-10-01", LocalDate::from);
                System.out.println(date);
            }).start();
        }
    }
}
