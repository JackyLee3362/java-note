package edu.note.java.time.jdk7;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDate {
    @Test
    @DisplayName("jdk7 - Date 对象")
    void test01() {

        // 默认当前时间
        Date current = new Date();

        // 创建对象表示一个指定的时间
        Date origin = new Date(0L);
        Assertions.assertEquals("Thu Jan 01 08:00:00 CST 1970", origin.toString());

        // setTime 修改时间
        // 1000毫秒=1秒
        origin.setTime(1000L);
        Assertions.assertEquals("Thu Jan 01 08:00:01 CST 1970", origin.toString());

        // getTime获取当前时间的毫秒值
        Assertions.assertEquals(1000, origin.getTime());

        // 变成 Instant
        origin.toInstant();

        // 比较大小
        Assertions.assertTrue(current.after(origin));
        Assertions.assertTrue(origin.equals(origin));
        Assertions.assertTrue(origin.equals(current));

    }
}
