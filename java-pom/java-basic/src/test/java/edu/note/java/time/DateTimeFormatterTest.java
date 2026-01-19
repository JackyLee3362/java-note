package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeFormatterTest {

    @Test
    void test02() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = dtf.parse("2018-10-01", LocalDate::from);
        assertEquals(LocalDate.of(2018, 10, 1), date);
        assertEquals(LocalDate.of(2018, 10, 1), date);
    }



    @Test
    void test08() {
        DateTimeFormatter stf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = stf.parse("1951-04-21");
                assertEquals(0, parse);
            }).start();
        }
    }

    @Test
    @DisplayName("日期时间 解析 格式化")
    void test10() {
        /*
         * static DateTimeFormatter ofPattern(格式) 获取格式对象
         * String format(时间对象) 按照指定方式格式化
         */

        // 1. 获取时间对象
        ZonedDateTime time = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));

        // 2. 解析/格式化器
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm;ss EE a");

        // 3. 格式化
        assertEquals(0, dtf1.format(time));

        // 4. 解析
        String dateTimeString = "2023-11-21 13:33:23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 解析字符串为 LocalDateTime 对象
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        // 输出解析后的 LocalDateTime 对象
        assertEquals(0, dateTime);
    }
}
