package edu.note.java.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 不建议用的过期方法
@Deprecated
public class DateTest {
    @Test
    @DisplayName("jdk7 - Date 对象")
    void test01() {

        // 默认当前时间
        Date current = new Date();

        // 创建对象表示一个指定的时间
        Date origin = new Date(0L);
        assertEquals("Thu Jan 01 08:00:00 CST 1970", origin.toString());

        // setTime 修改时间
        // 1000毫秒=1秒
        origin.setTime(1000L);
        assertEquals("Thu Jan 01 08:00:01 CST 1970", origin.toString());

        // getTime获取当前时间的毫秒值
        assertEquals(1000, origin.getTime());

        // 变成 Instant
        origin.toInstant();

        // 比较大小
        assertTrue(current.after(origin));
        assertTrue(origin.equals(origin));
        assertTrue(origin.before(current));

    }

    @Test
    void test_date_to_instant() {
        Date date = new Date();
        // UTC 时间
        Instant instant = date.toInstant();
        assertEquals(0, instant);
        // 当地时间
        Instant localInstant = instant.atZone(ZoneId.systemDefault()).toInstant();
        assertEquals(0, ZoneId.systemDefault());
        assertEquals(0, localInstant);
    }

    @Test
    @DisplayName("(JDK7) 构造器")
    void test02() {
        // 当前时间的日历对象
        // 注意，无法 new
        Calendar.getInstance();
        // 月份范围 0-11
        assertEquals(0, Calendar.JANUARY);
        assertEquals(11, Calendar.DECEMBER);
        // 星期范围 1-7
        assertEquals(1, Calendar.SUNDAY);
        assertEquals(7, Calendar.SATURDAY);
    }

    @Test
    @DisplayName("JDK7 - Calendar")
    void test03() {

        Calendar calendar = Calendar.getInstance();

        // 2.修改一下日历代表的时间
        calendar.setTime(new Date(1000));
        // assertEquals("Thu Jan 01 08:00:01 CST 1970", calendar.getTime());

        // public void set(int field, int value) 修改日历的某个字段信息
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 10);

        // public int get(int field) 取日期中的某个字段信息
        assertEquals(2023, calendar.get(Calendar.YEAR));
        assertEquals(8, calendar.get(Calendar.MONTH));
        assertEquals(10, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(1, calendar.get(Calendar.DAY_OF_WEEK));

        // public void add(int field, int amount) 为某个字段增加/减少指定的值
        calendar.add(Calendar.MONTH, -1);
        assertEquals(7, calendar.get(Calendar.MONTH));
    }

    @Test
    @DisplayName("判断是平年还是闰年")
    void test04() {

        // jdk7
        // 我们可以把时间设置为2000年3月1日
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.MARCH, 1);
        // 月份的范围:0~11
        // 再把日历往前减一天
        c.add(Calendar.DAY_OF_MONTH, -1);
        // 看当前的时间是28号还是29号?
        int day = c.get(Calendar.DAY_OF_MONTH);
        assertEquals(0, day);

    }
}
