package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-01-17 11:57
 */
public class ZonedDateTimeTest {
    
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
