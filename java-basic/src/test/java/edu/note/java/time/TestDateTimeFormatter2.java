package edu.note.java.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDateTimeFormatter2 {

    @Test
    @DisplayName("日期时间 解析 格式化")
    void test() {
        /*
         * static DateTimeFormatter ofPattern(格式) 获取格式对象
         * String format(时间对象) 按照指定方式格式化
         */

        // 1. 获取时间对象
        ZonedDateTime time = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));

        // 2. 解析/格式化器
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm;ss EE a");

        // 3. 格式化
        Assertions.assertEquals(0, dtf1.format(time));

        // 4. 解析
        String dateTimeString = "2023-11-21 13:33:23";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 解析字符串为 LocalDateTime 对象
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        // 输出解析后的 LocalDateTime 对象
        Assertions.assertEquals(0, dateTime);
    }
}
