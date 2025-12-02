package edu.note.java.cast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-12-02 10:11
 */
public class IntegerLong {
    @Test
    @DisplayName("测试包装类 Integer 与 Long 比较")
    void test01() {
        Integer num = 128;
        Long longNum = 128L;

        assertEquals(num, longNum.intValue());
        assertTrue(Objects.equals(num, longNum.intValue()));
        assertFalse(Objects.equals(num, longNum));
    }

    @Test
    @DisplayName("测试基本类 Integer 与 Long 比较")
    void test02() {
        int num = 128;
        long longNum = 128L;

        // 这里会自动装箱转换，等同于 Objects.equals(Integer.valueOf(num), Long.valueOf(longNum))
        assertFalse(Objects.equals(num, longNum));
        assertFalse(Objects.equals(Integer.valueOf(num), Long.valueOf(longNum)));
        assertTrue(num == longNum);
    }

}
