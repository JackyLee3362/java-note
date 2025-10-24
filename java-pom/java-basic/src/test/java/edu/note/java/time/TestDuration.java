package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDuration {
    @Test
    @DisplayName("Duration 时间间隔")
    void test01() {

        LocalDateTime t1 = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        LocalDateTime t2 = LocalDateTime.of(2001, 2, 3, 4, 5, 6);

        Duration duration = Duration.between(t1, t2);// 第二个参数减第一个参数

        assertEquals(399, duration.toDays());// 两个时间差的天数
        assertEquals(9580, duration.toHours());// 两个时间差的小时数
        assertEquals(574805, duration.toMinutes());// 两个时间差的分钟数
        assertEquals(344883000, duration.toMillis());// 两个时间差的毫秒数
        assertEquals(344883000000L, duration.toNanos());// 两个时间差的纳秒数
    }

    @Test
    @DisplayName("(太麻烦,不建议)ChronoUnit 时间间隔")
    void test02() {

        LocalDateTime t1 = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        LocalDateTime t2 = LocalDateTime.of(2001, 2, 3, 4, 5, 6);

        assertEquals(1, ChronoUnit.YEARS.between(t1, t2), "相差的年数");
        assertEquals(13, ChronoUnit.MONTHS.between(t1, t2), "相差的月数");
        assertEquals(57, ChronoUnit.WEEKS.between(t1, t2), "相差的周数");
        assertEquals(399, ChronoUnit.DAYS.between(t1, t2), "相差的天数");
        assertEquals(9580, ChronoUnit.HOURS.between(t1, t2), "相差的时数");
        assertEquals(574805, ChronoUnit.MINUTES.between(t1, t2), "相差的分数");
        assertEquals(34488306, ChronoUnit.SECONDS.between(t1, t2), "相差的秒数");
        assertEquals(34488306000L, ChronoUnit.MILLIS.between(t1, t2), "相差的毫秒数");
        assertEquals(34488306000000L, ChronoUnit.MICROS.between(t1, t2), "相差的微秒数");
        assertEquals(34488306000000000L, ChronoUnit.NANOS.between(t1, t2), "相差的纳秒数");
        assertEquals(798, ChronoUnit.HALF_DAYS.between(t1, t2), "相差的半天数");
        assertEquals(0, ChronoUnit.DECADES.between(t1, t2), "相差的十年数");
        assertEquals(0, ChronoUnit.CENTURIES.between(t1, t2), "相差的世纪(百年)数");
        assertEquals(0, ChronoUnit.MILLENNIA.between(t1, t2), "相差的千年数");
        assertEquals(0, ChronoUnit.ERAS.between(t1, t2), "相差的纪元数");
    }

    @Test
    @DisplayName("(JDK7,不建议)测试 Period - LocalDate")
    void test03() {

        // 生日的 年月日
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        LocalDate today = LocalDate.of(2001, 2, 3);

        Period period = Period.between(birthDate, today);// 第二个参数减第一个参数

        assertEquals(0, "相差的时间间隔对象:" + period);
        assertEquals(0, period.getYears());
        assertEquals(0, period.getMonths());
        assertEquals(0, period.getDays());

        assertEquals(0, period.toTotalMonths());
    }
}
