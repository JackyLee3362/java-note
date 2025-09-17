package edu.algorithm.sort;

import org.junit.jupiter.api.Test;

public class QuickSortTest {
    @Test
    void testSort() {
        Integer[] array = { 1, 0, 2, 4, 5, 6, 9, 3, 8, 7 };
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);
        SortUtils.print(array);
    }

}
