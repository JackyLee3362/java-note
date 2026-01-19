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
public class InstantTest {
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
        assertEquals(2026, time.getYear());

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


}
