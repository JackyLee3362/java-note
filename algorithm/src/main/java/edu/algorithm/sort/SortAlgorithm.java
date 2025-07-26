package edu.algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 排序算法接口
 */
public interface SortAlgorithm {
    /**
     * 对数组进行排序
     * T 表示 该参数必须实现 Comparable<T> 接口
     *
     * @param unsorted - 无序数组
     * @return 有序数组
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);

    /**
     * Auxiliary method for algorithms what wanted to work with lists from JCF
     * JCF: Java Collection Framework，Java容器框架
     *
     * @param unsorted - 无序数组
     * @return 有序数组
     */
    @SuppressWarnings("unchecked")
    default <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        return Arrays.asList(sort(unsorted.toArray((T[]) new Comparable[unsorted.size()])));
    }
}