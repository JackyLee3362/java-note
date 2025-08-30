package edu.note.java.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

/**
 * @author jackylee
 * @date 2025/7/9 15:41
 */
public class TestLocalDateTime {

    @Test
    // NOTE 本地时间
    @DisplayName("创建本地日期时间")
    void test01() {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(10);
        Date startDate = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
        Assertions.assertEquals(0, startDate);
        Assertions.assertEquals(0, endDate);
        // Assertions.assertEquals(0,startTime.toLocalDate());
        // Assertions.assertEquals(0,startTime.toLocalTime());
        // Assertions.assertEquals(0,endTime.toLocalDate());
        // Assertions.assertEquals(0,endTime.toLocalTime());
    }

    @Test
    void test_get_current_timestamp() {
        Assertions.assertEquals(0, System.currentTimeMillis());
    }

    @Test
    @DisplayName("测试")
    void test03() {
        // 当前时间的的日历对象(包含年月日时分秒)
        LocalDateTime nowDateTime = LocalDateTime.now();

        Assertions.assertEquals(0, "今天是:" + nowDateTime);// 今天是：
        Assertions.assertEquals(0, nowDateTime.getYear());// 年
        Assertions.assertEquals(0, nowDateTime.getMonthValue());// 月
        Assertions.assertEquals(0, nowDateTime.getDayOfMonth());// 日
        Assertions.assertEquals(0, nowDateTime.getHour());// 时
        Assertions.assertEquals(0, nowDateTime.getMinute());// 分
        Assertions.assertEquals(0, nowDateTime.getSecond());// 秒
        Assertions.assertEquals(0, nowDateTime.getNano());// 纳秒
        // 日:当年的第几天
        Assertions.assertEquals(0, "dayofYear:" + nowDateTime.getDayOfYear());
        // 星期
        Assertions.assertEquals(0, nowDateTime.getDayOfWeek());
        Assertions.assertEquals(0, nowDateTime.getDayOfWeek().getValue());
        // 月份
        Assertions.assertEquals(0, nowDateTime.getMonth());
        Assertions.assertEquals(0, nowDateTime.getMonth().getValue());

        LocalDate ld = nowDateTime.toLocalDate();
        LocalTime lt = nowDateTime.toLocalTime();
    }

}
