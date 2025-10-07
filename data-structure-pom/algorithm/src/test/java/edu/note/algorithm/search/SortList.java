package edu.note.algorithm.search;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class SortList {
    public static void test1() {
        // 一维数组排序
        int[] list = { 10, 2, 5, 9, 3 };
        Integer[] listObject = { 10, 2, 5, 9, 3 };

        // 原地升序排序
        Arrays.sort(list);
        Arrays.sort(listObject);
        System.out.println("list升序排序后" + Arrays.toString(list));
        System.out.println("listObject升序排序" + Arrays.toString(listObject));

        // 原地降序排序
        Arrays.sort(listObject, Collections.reverseOrder());
        System.out.println("listObject降序排序" + Arrays.toString(listObject));

    }

    public static void test2() {
        Integer[] listObject = { 1, 2, 5, 9, 3 };
        // 自定义排序-不同语法的实现
        // 自定义排序-lambda表达式
        Arrays.sort(listObject, (a, b) -> b - a); // 达到同样降序排序的效果
        System.out.println("listObject自定义排序（lambda表达式）" + Arrays.toString(listObject));

        // 自定义排序-接口
        Arrays.sort(listObject, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        System.out.println("listObject自定义排序（接口实现）" + Arrays.toString(listObject));

    }

    public static void main(String[] args) {
        test2();
    }
}