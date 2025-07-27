package edu.note.java.time;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import org.junit.jupiter.api.Test;

public class TestDateTimeFormatter {
    @Test
    public void test01() {
        DateTimeFormatter stf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = stf.parse("1951-04-21");
                System.out.println(parse);
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
                        System.out.println(e);
                    }
                }
            }).start();
        }
    }
}
