package edu.note.java.time;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

/**
 * @author jackylee
 * @date 2025/7/9 15:35
 */
public class DateTest {
    @Test
    void test_date_create() {
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    void test_date_to_instant() {
        Date date = new Date();
        // UTC 时间
        Instant instant = date.toInstant();
        System.out.println(instant);
        // 当地时间
        Instant localInstant = instant.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(ZoneId.systemDefault());
        System.out.println(localInstant);
    }

}
