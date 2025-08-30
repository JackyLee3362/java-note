package edu.note.java.time;

import java.time.ZoneId;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class TestZoneId {
    @Test
    @DisplayName("测试获取所有时区")
    void test() {
        // 1.获取所有的时区名称
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Assertions.assertEquals(0, zoneIds.size());// 600
        Assertions.assertEquals(0, zoneIds);// Asia/Shanghai

        // 2.获取当前系统的默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        Assertions.assertEquals(0, zoneId);// Asia/Shanghai

        // 3.获取指定的时区
        ZoneId zoneId1 = ZoneId.of("Asia/Pontianak");
        Assertions.assertEquals(0, zoneId1);// Asia/Pontianak
    }
}
