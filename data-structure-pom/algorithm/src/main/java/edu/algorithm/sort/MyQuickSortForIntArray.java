package edu.algorithm.sort;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序，但是该快排有些问题，每次 partition 后并不会确定最终位置
 * 无法解决一些问题，比如数组中第 k 大的数
 * 算法参考自 [TheAlgorithms/Java](https://github.com/TheAlgorithms/Java)
 * 
 * @author JackyLee
 * @date 2024/03/16
 */
@Slf4j
public final class MyQuickSortForIntArray {
    public void sort(int[] array) {
        log.info("开始对数组进行排序");
        doSort(array, 0, array.length - 1);
        log.debug("结束排序");
    }

    private void doSort(int[] array, int left, int right) {
        if (left < right) {
            // 指定 pivot，partition 后 array 数组应该是最终值
            log.debug("左=" + left + "，右=" + right);
            log.debug("排序前:" + Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
            int pivot = partition(array, left, right);
            log.debug("pivot=" + pivot + ",值为" + array[pivot]);
            log.debug("排序后:" + Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
            // 分区
            doSort(array, left, pivot - 1);
            // doSort(array, pivot + 1, right); 为什么不能这样写
            // 因为每次并没有让 pivot 定位下来
            doSort(array, pivot, right);
        }
    }

    /**
     * 指定 array 中的一个值 pivot，然后返回 idx
     * [left, idx] 所有的值小于 pivot
     * [idx+1, right] 所有的值大于或等于 pivot
     * partition 后 pivot 的值会是最终位置吗？该算法不会
     * 
     * @param array 数组
     * @param left  数组的第一个索引（包含）
     * @param right 数组的最后一个索引（包含）
     * @return
     */
    private int partition(int[] array, int left, int right) {
        int mid = (left + right) >>> 1; // 无符号右移
        int pivot = array[mid]; // 待比较的值 pivot
        // 每次循环，都会使得
        // array 中 [上一次 left, left ] 比 pivot 小
        // array 中 [上一次 right, right ] 比 pivot 大
        // 这里的循环为什么要有【等号】？
        /**
         * 答案：
         * 递归出口有 4 种可能，假设 pivot = 5
         * --------------------------------
         * 第 1 种：
         * a = [..., 3, 6, ...]
         * i = [..., l, r, ...]
         * 经过 while1 while2 后
         * a = [..., 3, 6, ...]
         * i = [..., r, l, ...]
         * --------------------------------
         * 第 2 种：
         * a = [..., 6, 3, ...]
         * i = [..., l, r, ...]
         * 经过 if 后
         * a = [..., 3, 6, ...]
         * i = [..., r, l, ...]
         * --------------------------------
         * 第 3 种：============> 如果没有等号，这个就会出错，返回的是 r 的位置，其实是 r + 1 的位置
         * a = [..., 3, _, ...] ( _ >= 3 )
         * i = [..., lr, , ...]
         * 经过 while1 后
         * a = [..., 3, _, ...]
         * i = [..., r, l, ...]
         * --------------------------------
         * 第 4 种：
         * a = [..., _, 6, ...] ( _ <= 6 )
         * i = [..., , lr, ...]
         * 经过 while2 后
         * a = [..., _, 6, ...]
         * i = [..., r, l, ...]
         */
        while (left <= right) {
            // 当 pivot > array[left] 时，指针右移，直到找到一个比 pivot 大的数
            while (pivot > array[left])
                ++left;
            // 当 pivot < array[right] 时，指针左移，直到找到一个比 pivot 小的数
            while (pivot < array[right])
                --right;
            // 当 left <= right 时，交换 left 处和 right 处的值的位置
            // 这里为什么要有【等号】？
            // 【等号】的话 swap 交换无妨，无等号的话，会死循环
            if (left <= right) {
                // 交换位置
                swap(array, left, right);
                ++left;
                --right;
            }
            log.debug(Arrays.toString(array));
        }
        return left;
    }

    private void swap(int[] array, int idx1, int idx2) {
        int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }
}
