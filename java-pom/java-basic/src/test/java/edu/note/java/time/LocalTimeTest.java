package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-01-17 11:55
 */
public class LocalTimeTest {

    // 获取本地时间的日历对象。(包含 时分秒)
    @Test
    @DisplayName("构造器")
    void test03() {
        LocalTime.now();
        LocalTime.of(14, 12);
        LocalTime.of(14, 12, 33);
        LocalTime time = LocalTime.of(14, 12, 33, 222);

        // with
        time.withHour(0);
        time.withMinute(0);
        time.withSecond(0);
        time.withNano(20);

    }

    @Test
    @DisplayName("获取当地时间")
    void test04() {
        LocalTime nowTime = LocalTime.of(14, 12, 33, 222);
        assertEquals(14, nowTime.getHour());
        assertEquals(12, nowTime.getMinute());
        assertEquals(33, nowTime.getSecond());
        assertEquals(222, nowTime.getNano());

        LocalTime mTime = LocalTime.of(8, 20, 30, 150);

        // is系列的方法
        assertEquals(true, nowTime.isBefore(mTime));
        assertEquals(false, nowTime.isAfter(mTime));

        // with系列的方法，只能修改时、分、秒
        assertEquals(0, nowTime.withHour(10));

        // plus系列的方法，只能修改时、分、秒
        assertEquals(0, nowTime.plusHours(10));

    }
}
