package edu.note.java.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestLocalDate {
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
    @DisplayName("方法")
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

        // 判断
        // 平年闰年
        Assertions.assertEquals(false, date.isLeapYear());
        Assertions.assertEquals(false, date.isAfter(date));
        Assertions.assertEquals(false, date.isBefore(date));
    }

    @Test
    @DisplayName("判断生日")
    void test03() {

        LocalDate birthday = LocalDate.of(2000, 10, 18);
        LocalDate current = LocalDate.of(2025, 10, 18);

        MonthDay birthMonthDay = MonthDay.of(birthday.getMonthValue(), birthday.getDayOfMonth());
        MonthDay curMonthDay = MonthDay.from(current);

        // 判断今天是否是你的生日
        Assertions.assertEquals(birthMonthDay, curMonthDay);

    }
}
