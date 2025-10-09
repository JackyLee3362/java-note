package edu.note.algorithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class SortUtils {

    /**
     * 交换数组中给定位置的两个元素。
     *
     * @param array 交换元素的数组
     * @param i     第一个要交换的元素的索引
     * @param j     要交换的第二个元素的索引
     * @param <T>   数组中元素的类型
     */
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 比较两个元素，看看第一个元素是否小于第二个元素
     *
     * @param firstElement  要比较的第一个元素
     * @param secondElement 要比较的第二个元素
     * @return true 如果第一个元素小于第二个元素，否则 false
     */
    public static <T extends Comparable<T>> boolean less(T firstElement, T secondElement) {
        return firstElement.compareTo(secondElement) < 0;
    }

    /**
     * 比较两个元素，看看【第一个元素】是否大于【第二个元素】
     *
     * @param firstElement  要比较的第一个元素
     * @param secondElement 要比较的第二个元素
     * @return true 如果第一个元素大于第二个元素，否则 false
     */
    public static <T extends Comparable<T>> boolean greater(T firstElement, T secondElement) {
        return firstElement.compareTo(secondElement) > 0;
    }

    /**
     * 比较两个元素，看【第一个元素】是否大于等于【第二个元素】
     *
     * @param firstElement  要比较的第一个元素
     * @param secondElement 要比较的第二个元素
     * @return true 如果第一个元素大于或等于第二个元素，
     *         否则为假
     */
    static <T extends Comparable<T>> boolean greaterOrEqual(T firstElement, T secondElement) {
        return firstElement.compareTo(secondElement) >= 0;
    }

    /**
     * 将列表的元素打印到标准输出
     *
     * @param listToPrint 要打印的列表
     */
    static void print(List<?> listToPrint) {
        String result = listToPrint.stream().map(Object::toString).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    /**
     * 将数组的元素打印到标准输出。
     *
     * @param array 要打印的数组
     */
    static <T> void print(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * 翻转数组指定范围内元素的顺序。
     *
     * @param array 要翻转元素的数组
     * @param left  要翻转的范围的左边界（包含）
     * @param right 要翻转的范围的右边界（包含）
     */
    public static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
        while (left <= right) {
            swap(array, left++, right--);
        }
    }

    /**
     * 检查数组是否按升序排序
     *
     * @param array 要检查的数组
     * @return 如果数组按升序排序则返回 true，否则返回 false
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查列表是否按升序排序。
     *
     * @param list 要检查的列表
     * @return true 如果列表按升序排序，否则 false
     */
    public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (less(list.get(i), list.get(i - 1))) {
                return false;
            }
        }
        return true;
    }
}
