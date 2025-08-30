package edu.note.java.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestInstant {
    @Test
    @DisplayName("标准时间")
    void test() {

        // 1. 获取当前时间的Instant对象(标准时间)
        Instant now = Instant.now();
        Assertions.assertInstanceOf(Instant.class, now);

        // 2. 根据(秒/毫秒/纳秒)获取Instant对象
        Instant instant1 = Instant.ofEpochMilli(0L);
        Assertions.assertInstanceOf(Instant.class, instant1);
        Assertions.assertEquals("1970-01-01T00:00:00Z", instant1.toString());

        Instant instant2 = Instant.ofEpochSecond(1L);
        Assertions.assertInstanceOf(Instant.class, instant2);
        Assertions.assertEquals("1970-01-01T00:00:01Z", instant2.toString());

        Instant instant3 = Instant.ofEpochSecond(1L, 1000000000L);
        Assertions.assertInstanceOf(Instant.class, instant3);
        Assertions.assertEquals("1970-01-01T00:00:02Z", instant3.toString());

        // 3. 指定时区
        ZonedDateTime time = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        Assertions.assertInstanceOf(ZonedDateTime.class, time);
        Assertions.assertEquals("Asia/Shanghai", time.getZone().getId());

        // 4. isXxx 判断
        Instant instant4 = Instant.ofEpochMilli(0L);
        Instant instant5 = Instant.ofEpochMilli(1000L);
        Assertions.assertTrue(instant4.isBefore(instant5));
        Assertions.assertFalse(instant4.isAfter(instant5));

        // 5. minus/plusMillis(long millisToSubtract) 减少时间系列的方法
        Instant instant6 = instant5.minusMillis(500L);
        Assertions.assertEquals("1970-01-01T00:00:00.500Z", instant6.toString());
        Instant instant7 = instant5.plusMillis(1000L);
        Assertions.assertEquals("1970-01-01T00:00:02Z", instant7.toString());

        // 6. minus/plusSeconds(long secondsToAdd) 方法
        Instant instant8 = instant5.minusSeconds(1);
        Assertions.assertEquals("1970-01-01T00:00:00Z", instant8.toString());
        Instant instant9 = instant5.plusSeconds(1);
        Assertions.assertEquals("1970-01-01T00:00:02Z", instant9.toString());

    }
}
