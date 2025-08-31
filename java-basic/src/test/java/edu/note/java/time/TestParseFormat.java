package edu.note.java.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestParseFormat {

    @Test
    @DisplayName("SimpleDateFormat 存在线程安全问题")
    void test01() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse("1951-04-21");
        Assertions.assertEquals(new Date(1951 - 1900, 4 - 1, 21), date);
        Assertions.assertEquals("1951-04-21", sdf.format(date));

    }

    @Test
    void test02() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = dtf.parse("2018-10-01", LocalDate::from);
        Assertions.assertEquals(LocalDate.of(2018, 10, 1), date);
        Assertions.assertEquals(LocalDate.of(2018, 10, 1), date);
    }

    @Test
    @DisplayName("")
    void test03() throws ParseException {

        /*
         * public simpleDateFormat() 默认格式
         * public simpleDateFormat(String pattern) 指定格式
         * public final string format(Date date) 格式化(日期对象 -> 字符串)
         * public Date parse(string source) 解析(字符串 -> 日期对象)
         */

        String str = "2023-11-11 11:11:11";
        // 2. 利用空参构造创建simpleDateFormat对象
        // 细节:
        // 创建对象的格式要跟字符串的格式完全一致
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(str);
        // 3.打印结果
        Assertions.assertEquals(1699672271000L, date.getTime()); // 1699672271000

    }

    @Test
    @DisplayName("")
    void test06() throws ParseException {

        // 1.可以通过 2000-11-11 进行解析，解析成一个Date对象
        String str = "2000-11-11";
        // 2.解析
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(str);
        // 3.格式化
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String result = sdf2.format(date);
        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("")
    void test07() throws ParseException {
        /*
         * 需求:
         * 秒杀活动开始时间:2023年11月11日 0:0:0(毫秒值)
         * 秒杀活动结束时间:2023年11月11日 0:10:0(毫秒值)
         * 
         * 小贾下单并付款的时间为:2023年11月11日 0:01:0
         * 小皮下单并付款的时间为:2023年11月11日 0:11:0
         * 用代码说明这两位同学有没有参加上秒杀活动?
         */

        // 1.定义字符串表示三个时间
        String startStr = "2023年11月11日 0:0:0";
        String endStr = "2023年11月11日 0:10:0";
        String orderStr = "2023年11月11日 0:01:00";
        // 2.解析上面的三个时间，得到Date对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date startDate = sdf.parse(startStr);
        Date endDate = sdf.parse(endStr);
        Date orderDate = sdf.parse(orderStr);

        // 3.得到三个时间的毫秒值
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long orderTime = orderDate.getTime();

        // 4.判断
        if (orderTime >= startTime && orderTime <= endTime) {
            Assertions.assertEquals(0, "参加秒杀活动成功");
        } else {
            Assertions.assertEquals(0, "参加秒杀活动失败");
        }

    }

    @Test
    void test08() {
        DateTimeFormatter stf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = stf.parse("1951-04-21");
                Assertions.assertEquals(0, parse);
            }).start();
        }
    }

    @Test
    void test09() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (sdf) {
                    try {
                        Assertions.assertEquals(0, sdf.parse("1951-04-21"));
                    } catch (Exception e) {
                        Assertions.assertEquals(0, e);
                    }
                }
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
