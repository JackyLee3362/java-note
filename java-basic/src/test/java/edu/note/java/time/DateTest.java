package edu.note.java.time;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author jackylee
 * @date 2025/7/9 15:35
 */
public class DateTest {
    @Test
    void test_date_create() {
        Date date = new Date();
        Assertions.assertEquals(0, date);
    }

    @Test
    void test_date_to_instant() {
        Date date = new Date();
        // UTC 时间
        Instant instant = date.toInstant();
        Assertions.assertEquals(0, instant);
        // 当地时间
        Instant localInstant = instant.atZone(ZoneId.systemDefault()).toInstant();
        Assertions.assertEquals(0, ZoneId.systemDefault());
        Assertions.assertEquals(0, localInstant);
    }

}
