package edu.algorithm.sort;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序，每次 while 后，都能确定一个值的最终位置
 * 
 * @author Jacky Lee
 * @date 2024/03/16
 */
@Slf4j
public class MyQuickSortForIntArrayFromIOWiki {
    public void sort(int[] array) {
        doSort(array, 0, array.length - 1);
    }

    /**
     * 每次调用，都会确定一个 pivot 值的最终位置
     * [left, pivot-1], pivot, [pivot+1, right]
     * pivot 左边 < pivot
     * pivot 右边 >= pivot
     * 
     * 
     * @param array
     * @param left
     * @param right
     */
    private void doSort(int[] array, int left, int right) {
        // 递归出口
        if (left >= right) {
            return;
        }
        // 定义 pivot
        int pivot = array[left];
        int lo = left, hi = right;
        /**
         * 这里 为什么不加等号？
         * 如果加 等号，那么 当 lo == hi 时，就会死循环
         */
        log.debug(Arrays.toString(array) + "===>开始");
        while (lo < hi) {
            // 直到找到比 pivot 小的值
            while (lo < hi && array[hi] >= pivot)
                --hi;

            array[lo] = array[hi];
            // 直到找到比 pivot 大或者等于的值
            while (lo < hi && array[lo] < pivot)
                ++lo;
            array[hi] = array[lo];
        }

        array[lo] = pivot;
        log.debug(Arrays.toString(array) + "===>结束");
        doSort(array, left, lo - 1);
        doSort(array, lo + 1, right);
    }
}
