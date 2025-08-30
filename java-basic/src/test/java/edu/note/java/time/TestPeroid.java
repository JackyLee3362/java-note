package edu.note.java.time;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPeroid {
    @Test
    @DisplayName("测试 Period")
    void test() {

        // 当前本地 年月日
        LocalDate today = LocalDate.now();
        Assertions.assertEquals(0, today);

        // 生日的 年月日
        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        Assertions.assertEquals(0, birthDate);

        Period period = Period.between(birthDate, today);// 第二个参数减第一个参数

        Assertions.assertEquals(0, "相差的时间间隔对象:" + period);
        Assertions.assertEquals(0, period.getYears());
        Assertions.assertEquals(0, period.getMonths());
        Assertions.assertEquals(0, period.getDays());

        Assertions.assertEquals(0, period.toTotalMonths());
    }
}
