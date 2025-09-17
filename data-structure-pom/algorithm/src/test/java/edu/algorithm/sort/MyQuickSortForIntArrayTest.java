package edu.algorithm.sort;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MyQuickSortForIntArrayTest {
    @Test
    void testSort() {
        // 设置日志级别
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        MyQuickSortForIntArray qs = new MyQuickSortForIntArray();
        int[] a1 = { 8, 4, 5, 6, 9, 3, 2, 7, 1, 0 };
        qs.sort(a1);
        System.out.println(Arrays.toString(a1));
    }
}
