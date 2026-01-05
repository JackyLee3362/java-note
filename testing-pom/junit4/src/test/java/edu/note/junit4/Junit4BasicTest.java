package edu.note.junit4;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author jackylee
 * @date 2025-10-09 16:16
 */
public class Junit4BasicTest {

    @Test
    public void test01() {
        assertEquals(0, 0);
    }

    @Test
    @Ignore("跳过测试")
    public void test02() {
        assertEquals(0, 0);
        // 错误写法
        // assertEquals(0, Integer.valueOf(2));
    }
}
