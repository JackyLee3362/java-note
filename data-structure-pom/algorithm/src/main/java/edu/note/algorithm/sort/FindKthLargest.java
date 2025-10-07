package edu.note.algorithm.sort;

/**
 * 找到数组中第 k 大的数
 * 
 * @author Jacky Lee
 * @date 2024/03/16
 */
public class FindKthLargest {
    int kthAscFrom0;

    public int findKthLargest(int[] array, int kthDescFrom1) {
        kthAscFrom0 = array.length - 1 - (kthDescFrom1 - 1);
        int idx = find(array, 0, array.length - 1);
        return array[idx];
    }

    private int find(int[] array, int left, int right) {
        // 递归出口（其实不会有这种可能）
        if (left >= right) {
            return left;
        }
        int pivot = array[left];
        int lo = left, hi = right;
        while (lo < hi) {
            while (lo < hi && array[hi] >= pivot)
                --hi;
            array[lo] = array[hi];
            while (lo < hi && array[lo] < pivot)
                ++lo;
            array[hi] = array[lo];
        }
        array[lo] = pivot;
        if (lo < kthAscFrom0) {
            return find(array, lo + 1, right);
        } else if (lo > kthAscFrom0) {
            return find(array, left, lo - 1);
        }
        // 说明相等
        return lo;
    }
}
