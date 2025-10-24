package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 是 UTC 时间，全球统一
public class TestInstant {
    @Test
    @DisplayName("时区")
    void testZone() {
        // 1.获取所有的时区名称
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        assertTrue(zoneIds.size() > 600);// 603
        assertTrue(zoneIds.contains("Asia/Shanghai"));

        // 2.获取当前系统的默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        assertEquals("Asia/Shanghai", zoneId.getId());

        // 3.获取指定的时区
        ZoneId zoneId1 = ZoneId.of("Asia/Tokyo");
        assertEquals("Asia/Tokyo", zoneId1.getId());
    }

    @Test
    @DisplayName("UTC 时间")
    void test01() {

        // 当前时间(标准时间)
        Instant.now();

        // 根据(秒/毫秒/纳秒)构造器
        Instant i1 = Instant.ofEpochMilli(0L);
        assertEquals("1970-01-01T00:00:00Z", i1.toString());

        Instant i2 = Instant.ofEpochSecond(1L);
        assertEquals("1970-01-01T00:00:01Z", i2.toString());

        Instant i3 = Instant.ofEpochSecond(1L, 1000000000L);
        assertEquals("1970-01-01T00:00:02Z", i3.toString());
    }

    @Test
    @DisplayName("标准时间")
    void test02() {

        // 3. 指定时区
        ZonedDateTime time = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        assertEquals("Asia/Shanghai", time.getZone().getId());
        assertEquals(2025, time.getYear());

        // 4. isXxx 判断
        Instant i1 = Instant.ofEpochMilli(0L);
        Instant i2 = Instant.ofEpochMilli(1000L);
        assertTrue(i1.isBefore(i2));
        assertFalse(i1.isAfter(i2));

        // 5. minus/plusMillis(long millisToSubtract) 减少时间系列的方法
        Instant i3 = i2.minusMillis(500L);
        assertEquals("1970-01-01T00:00:00.500Z", i3.toString());
        Instant i4 = i2.plusMillis(1000L);
        assertEquals("1970-01-01T00:00:02Z", i4.toString());

        // 6. minus/plusSeconds(long secondsToAdd) 方法
        Instant i5 = i2.minusSeconds(1);
        assertEquals("1970-01-01T00:00:00Z", i5.toString());
        Instant i6 = i2.plusSeconds(1);
        assertEquals("1970-01-01T00:00:02Z", i6.toString());

    }

    @Test
    @DisplayName("时区日期时间")
    void test03() {

        /*
         * static ZonedDateTime now() 获取当前时间的ZonedDateTime对象
         * static ZonedDateTime ofXxx(long epochMilli) 获取指定时间的ZonedDateTime对象
         * ZonedDateTime withXxx(时间) 修改时间系列的方法
         * ZonedDateTime minusXxx(时间) 减少时间系列的方法
         * ZonedDateTime plusXxx(时间) 增加时间系列的方法
         */

        // 1. 获取当前时间对象(带时区)
        ZonedDateTime now = ZonedDateTime.now();
        assertEquals(0, now);

        // 2. 获取指定的时间对象(带时区)1/年月日时分秒纳秒方式指定
        ZonedDateTime time1 = ZonedDateTime.of(2023, 10, 1, 11, 12, 12, 0, ZoneId.of("Asia/Shanghai"));
        assertEquals(0, time1);

        // 通过Instant + 时区的方式指定获取时间对象
        Instant instant = Instant.ofEpochMilli(0L);
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime time2 = ZonedDateTime.ofInstant(instant, zoneId);
        assertEquals(0, time2);

        // 3. withXxx 修改时间系列的方法
        ZonedDateTime time3 = time2.withYear(2000);
        assertEquals(0, time3);

        // 4. 减少时间
        ZonedDateTime time4 = time3.minusYears(1);
        assertEquals(0, time4);

        // 5. 增加时间(plusYears, plusMonths, plusDays, ...)
        ZonedDateTime time5 = time4.plusYears(1);
        assertEquals(0, time5);

    }

}
