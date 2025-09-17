package edu.algorithm.sort;

import static edu.algorithm.sort.SortUtils.*;

/**
 * 快速排序
 */
class QuickSort implements SortAlgorithm {

    /**
     * 通用排序
     *
     * @param array 待排序的数组
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 排序过程
     *
     * @param array 待排序的数组
     * @param left  数组的第一个索引
     * @param right 数组的最后一个索引
     */
    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = randomPartition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot, right);
        }
    }

    /**
     * 随机化数组以避免基本有序的序列
     *
     * @param array 待排序的数组
     * @param left  数组的第一个索引
     * @param right 数组的最后一个索引
     * @return 数组的分区索引
     */
    private static <T extends Comparable<T>> int randomPartition(T[] array, int left, int right) {
        // int randomIndex = left + (int) (Math.random() * (right - left + 1));
        // swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    /**
     * 该方法查找数组的分区索引
     *
     * @param array 待排序的数组
     * @param left  数组的第一个索引
     * @param right 数组的最后一个索引 查找数组的分区索引 partition
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) >>> 1;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                ++left;
            }
            while (less(pivot, array[right])) {
                --right;
            }
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }
}
