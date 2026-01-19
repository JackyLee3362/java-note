package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-01-17 11:55
 */
public class LocalDateTest {

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
}
