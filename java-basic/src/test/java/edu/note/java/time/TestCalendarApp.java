package edu.note.java.time;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class TestCalendarApp {

    @Test
    @DisplayName("判断是平年还是闰年")
    void test() {

        // 判断任意的一个年份是闰年还是平年要求:用JDK7和JDK8两种方式判断提示:

        // jdk7
        // 我们可以把时间设置为2000年3月1日
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.MARCH, 1);
        // 月份的范围:0~11
        // 再把日历往前减一天
        c.add(Calendar.DAY_OF_MONTH, -1);
        // 看当前的时间是28号还是29号?
        int day = c.get(Calendar.DAY_OF_MONTH);
        Assertions.assertEquals(0, day);

    }
}
