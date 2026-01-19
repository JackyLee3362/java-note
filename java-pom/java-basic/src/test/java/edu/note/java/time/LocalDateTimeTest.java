package edu.note.java.time;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 完全是在本地解析的，不涉及时区
public class LocalDateTimeTest {

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
