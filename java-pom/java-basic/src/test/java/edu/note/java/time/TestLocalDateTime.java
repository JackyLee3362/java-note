package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
        assertEquals(2023, date.getYear());
        // 月
        assertEquals(Month.APRIL, date.getMonth());
        assertEquals(4, date.getMonthValue());
        // 日
        assertEquals(5, date.getDayOfMonth());
        assertEquals(95, date.getDayOfYear());
        assertEquals(DayOfWeek.WEDNESDAY, date.getDayOfWeek());
        // 月日
        assertEquals(MonthDay.of(4, 5), MonthDay.from(date));

        // 判断
        // 平年闰年
        assertEquals(false, date.isLeapYear());
        assertEquals(false, date.isAfter(date));
        assertEquals(false, date.isBefore(date));
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
