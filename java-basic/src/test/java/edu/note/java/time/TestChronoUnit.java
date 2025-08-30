package edu.note.java.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestChronoUnit {
    @Test
    @DisplayName("时间间隔测试")
    void test() {

        LocalDateTime t1 = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        LocalDateTime t2 = LocalDateTime.of(2001, 2, 3, 4, 5, 6);

        Assertions.assertEquals(1, ChronoUnit.YEARS.between(t1, t2), "相差的年数");
        Assertions.assertEquals(13, ChronoUnit.MONTHS.between(t1, t2), "相差的月数");
        Assertions.assertEquals(57, ChronoUnit.WEEKS.between(t1, t2), "相差的周数");
        Assertions.assertEquals(399, ChronoUnit.DAYS.between(t1, t2), "相差的天数");
        Assertions.assertEquals(9580, ChronoUnit.HOURS.between(t1, t2), "相差的时数");
        Assertions.assertEquals(574805, ChronoUnit.MINUTES.between(t1, t2), "相差的分数");
        Assertions.assertEquals(34488306, ChronoUnit.SECONDS.between(t1, t2), "相差的秒数");
        Assertions.assertEquals(34488306000L, ChronoUnit.MILLIS.between(t1, t2), "相差的毫秒数");
        Assertions.assertEquals(34488306000000L, ChronoUnit.MICROS.between(t1, t2), "相差的微秒数");
        Assertions.assertEquals(34488306000000000L, ChronoUnit.NANOS.between(t1, t2), "相差的纳秒数");
        Assertions.assertEquals(798, ChronoUnit.HALF_DAYS.between(t1, t2), "相差的半天数");
        Assertions.assertEquals(0, ChronoUnit.DECADES.between(t1, t2), "相差的十年数");
        Assertions.assertEquals(0, ChronoUnit.CENTURIES.between(t1, t2), "相差的世纪(百年)数");
        Assertions.assertEquals(0, ChronoUnit.MILLENNIA.between(t1, t2), "相差的千年数");
        Assertions.assertEquals(0, ChronoUnit.ERAS.between(t1, t2), "相差的纪元数");
    }
}
