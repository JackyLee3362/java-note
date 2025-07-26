package edu.algorithm.sort;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MyQuickSortForIntArrayFromIOWikiTest {
    @Test
    void testSort() {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        MyQuickSortForIntArrayFromIOWiki qs = new MyQuickSortForIntArrayFromIOWiki();
        int[] a1 = { 8, 4, 5, 6, 9, 3, 2, 7, 1, 0 };
        qs.sort(a1);
        System.out.println(Arrays.toString(a1));
    }

    @Test
    void testFindKthNumber(){
        MyQuickSortForIntArrayFromIOWiki qs = new MyQuickSortForIntArrayFromIOWiki();
        int[] a1 = { 8, 4, 5, 6, 9, 3, 2, 7, 1, 0 };
        qs.sort(a1);
        System.out.println(Arrays.toString(a1));

    }
}
