package edu.note.java.time;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDuration2 {
    @Test
    @DisplayName("日期时间 - 毫秒间隔")
    void test() {
        // 本地日期时间对象
        LocalDateTime today = LocalDateTime.now();
        Assertions.assertEquals(0, today);

        // 出生的日期时间对象
        LocalDateTime birthDate = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        Assertions.assertEquals(0, birthDate);

        Duration duration = Duration.between(birthDate, today);// 第二个参数减第一个参数
        Assertions.assertEquals(0, duration);

        Assertions.assertEquals(0, duration.toDays());// 两个时间差的天数
        Assertions.assertEquals(0, duration.toHours());// 两个时间差的小时数
        Assertions.assertEquals(0, duration.toMinutes());// 两个时间差的分钟数
        Assertions.assertEquals(0, duration.toMillis());// 两个时间差的毫秒数
        Assertions.assertEquals(0, duration.toNanos());// 两个时间差的纳秒数
    }
}
