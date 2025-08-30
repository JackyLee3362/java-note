package edu.note.java.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDuration {
    @Test
    @DisplayName("JDK7 - 计算间隔天数")
    void test01() throws ParseException {

        // 请使用代码实现计算你活了多少天，用JDK7和JDK8两种方式完成
        // JDK7
        // 规则:只要对时间进行计算或者判断，都需要先获取当前时间的毫秒值
        // 1.计算出生年月日的毫秒值
        String birthday = "2000-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(birthday);
        long birthdayTime = date.getTime();
        // 2.获取当前时间的毫秒值
        long todayTime = System.currentTimeMillis();
        // 3.计算间隔多少天
        long time = todayTime - birthdayTime;
        Assertions.assertEquals(0, time / 1000 / 60 / 60 / 24);
    }

    @Test
    @DisplayName("JDK8 - 计算间隔天数")
    void test02() {

        // JDK8
        LocalDate ld1 = LocalDate.of(2000, 1, 1);
        LocalDate ld2 = LocalDate.now();
        long days = ChronoUnit.DAYS.between(ld1, ld2);
        Assertions.assertEquals(0, days);

    }
}
