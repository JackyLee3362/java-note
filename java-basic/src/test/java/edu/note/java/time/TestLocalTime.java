package edu.note.java.time;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestLocalTime {
    // 获取本地时间的日历对象。(包含 时分秒)
    @Test
    @DisplayName("构造器")
    void test01() {
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
    void test02() {
        LocalTime nowTime = LocalTime.of(14, 12, 33, 222);
        Assertions.assertEquals(14, nowTime.getHour());
        Assertions.assertEquals(12, nowTime.getMinute());
        Assertions.assertEquals(33, nowTime.getSecond());
        Assertions.assertEquals(222, nowTime.getNano());

        LocalTime mTime = LocalTime.of(8, 20, 30, 150);

        // is系列的方法
        Assertions.assertEquals(true, nowTime.isBefore(mTime));
        Assertions.assertEquals(false, nowTime.isAfter(mTime));

        // with系列的方法，只能修改时、分、秒
        Assertions.assertEquals(0, nowTime.withHour(10));

        // plus系列的方法，只能修改时、分、秒
        Assertions.assertEquals(0, nowTime.plusHours(10));

    }
}
