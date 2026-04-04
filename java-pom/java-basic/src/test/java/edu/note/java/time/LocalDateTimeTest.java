package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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

    @Test
    @DisplayName("LocalDateTime 解析")
    void parse() {
        LocalDateTime dt = LocalDateTime.of(2025, 9, 1, 20, 10, 11);
        // 两者相等
        LocalDateTime parsed0 = LocalDateTime.parse("2025-09-01T20:10:11");
        LocalDateTime parsed1 = LocalDateTime.parse("2025-09-01T20:10:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // 自定义格式
        LocalDateTime parsed2 = LocalDateTime.parse("2025-09-01 20:10:11",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime parsed3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse("2025-09-01 20:10:11", LocalDateTime::from);

        assertEquals(parsed0, dt);
        assertEquals(parsed1, dt);
        assertEquals(parsed2, dt);
        assertEquals(parsed3, dt);
    }
}
