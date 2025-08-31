package edu.note.java.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 和时区相关
public class TestInstant {
    @Test
    @DisplayName("构造器")
    void test01() {

        // 当前时间(标准时间)
        Instant.now();

        // 根据(秒/毫秒/纳秒)构造器
        Instant i1 = Instant.ofEpochMilli(0L);
        Assertions.assertEquals("1970-01-01T00:00:00Z", i1.toString());

        Instant i2 = Instant.ofEpochSecond(1L);
        Assertions.assertEquals("1970-01-01T00:00:01Z", i2.toString());

        Instant i3 = Instant.ofEpochSecond(1L, 1000000000L);
        Assertions.assertEquals("1970-01-01T00:00:02Z", i3.toString());
    }

    @Test
    @DisplayName("标准时间")
    void test02() {

        // 3. 指定时区
        ZonedDateTime time = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        Assertions.assertEquals("Asia/Shanghai", time.getZone().getId());

        // 4. isXxx 判断
        Instant i4 = Instant.ofEpochMilli(0L);
        Instant i5 = Instant.ofEpochMilli(1000L);
        Assertions.assertTrue(i4.isBefore(i5));
        Assertions.assertFalse(i4.isAfter(i5));

        // 5. minus/plusMillis(long millisToSubtract) 减少时间系列的方法
        Instant i6 = i5.minusMillis(500L);
        Assertions.assertEquals("1970-01-01T00:00:00.500Z", i6.toString());
        Instant i7 = i5.plusMillis(1000L);
        Assertions.assertEquals("1970-01-01T00:00:02Z", i7.toString());

        // 6. minus/plusSeconds(long secondsToAdd) 方法
        Instant i8 = i5.minusSeconds(1);
        Assertions.assertEquals("1970-01-01T00:00:00Z", i8.toString());
        Instant i9 = i5.plusSeconds(1);
        Assertions.assertEquals("1970-01-01T00:00:02Z", i9.toString());

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
        Assertions.assertEquals(0, now);

        // 2. 获取指定的时间对象(带时区)1/年月日时分秒纳秒方式指定
        ZonedDateTime time1 = ZonedDateTime.of(2023, 10, 1, 11, 12, 12, 0, ZoneId.of("Asia/Shanghai"));
        Assertions.assertEquals(0, time1);

        // 通过Instant + 时区的方式指定获取时间对象
        Instant instant = Instant.ofEpochMilli(0L);
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime time2 = ZonedDateTime.ofInstant(instant, zoneId);
        Assertions.assertEquals(0, time2);

        // 3. withXxx 修改时间系列的方法
        ZonedDateTime time3 = time2.withYear(2000);
        Assertions.assertEquals(0, time3);

        // 4. 减少时间
        ZonedDateTime time4 = time3.minusYears(1);
        Assertions.assertEquals(0, time4);

        // 5. 增加时间(plusYears, plusMonths, plusDays, ...)
        ZonedDateTime time5 = time4.plusYears(1);
        Assertions.assertEquals(0, time5);

    }

    @Test
    @DisplayName("测试获取所有时区")
    void test04() {
        // 1.获取所有的时区名称
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Assertions.assertEquals(0, zoneIds.size());// 600
        Assertions.assertEquals(0, zoneIds);// Asia/Shanghai

        // 2.获取当前系统的默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        Assertions.assertEquals(0, zoneId);// Asia/Shanghai

        // 3.获取指定的时区
        ZoneId zoneId1 = ZoneId.of("Asia/Pontianak");
        Assertions.assertEquals(0, zoneId1);// Asia/Pontianak

    }
}
