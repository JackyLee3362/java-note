package edu.note.java.time;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 完全是在本地解析的，不涉及时区
public class TestLocalDateTime {

    @Test
    @DisplayName("构造器")
    void test01() {
        // 类方法: now
        LocalDate.now();

        // 类方法: of
        LocalDate date = LocalDate.of(2023, 4, 5);

        // 类方法 with
        date.withYear(2020);
        date.withMonth(3);
        date.withDayOfMonth(1);

        // 计算 minus/plus
        date.minusYears(1);
        date.minusMonths(2);
        date.minusDays(1);


    }

    @Test
    @DisplayName("属性")
    void test02() {
        LocalDate date = LocalDate.of(2023, 4, 5);
        // 年
        Assertions.assertEquals(2023, date.getYear());
        // 月
        Assertions.assertEquals(Month.APRIL, date.getMonth());
        Assertions.assertEquals(4, date.getMonthValue());
        // 日
        Assertions.assertEquals(5, date.getDayOfMonth());
        Assertions.assertEquals(95, date.getDayOfYear());
        Assertions.assertEquals(DayOfWeek.WEDNESDAY, date.getDayOfWeek());
        // 月日
        Assertions.assertEquals(MonthDay.of(4, 5), MonthDay.from(date));

        // 判断
        // 平年闰年
        Assertions.assertEquals(false, date.isLeapYear());
        Assertions.assertEquals(false, date.isAfter(date));
        Assertions.assertEquals(false, date.isBefore(date));
    }

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

    @Test
    @DisplayName("LocalDateTime 构造器")
    void testLocalDateTime() {

        // 当前时间
        LocalDateTime.now();
        // 指定时间
        LocalDateTime ldt = LocalDateTime.of(2025, 9, 1, 20, 10, 11);
        // 属性同 LocalDate / LocalTime
        // 转换
        Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        ldt.toLocalDate();
        ldt.toLocalTime();
        ldt.toInstant(ZoneOffset.UTC);

        // 生成特定时间的对象
        LocalDateTime with = ldt.withHour(18)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
            .with(DayOfWeek.THURSDAY);

    }
}
