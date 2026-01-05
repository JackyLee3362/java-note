package edu.note.java.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/17 17:26
 */
public class BigDecimalTest {

    @Test
    @DisplayName("测试 BigDecimal 构造器")
    public void test01() {
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal("1.0");
        assertEquals(b1, b2);
    }

    @Test
    @DisplayName("测试 BigDecimal 加减")
    void test02() {
        int i = BigDecimal.valueOf(3000).subtract(BigDecimal.valueOf(1000)).intValue();
        assertEquals(2000, i);
    }

    @Test
    @DisplayName("测试 BigDecimal 除法")
    void test03() {
        int i1 = BigDecimal.valueOf(1000).divide(BigDecimal.valueOf(3), 0, RoundingMode.HALF_UP).intValue();
        assertEquals(333, i1);
        String s1 = BigDecimal.valueOf(1000).divide(BigDecimal.valueOf(3), 1, RoundingMode.UP).toString();
        assertEquals("333.4", s1);
    }

}
