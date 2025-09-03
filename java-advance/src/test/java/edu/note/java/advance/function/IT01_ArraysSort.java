package edu.note.java.advance.function;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 需求：创建一个数组，进行倒序排列
public class IT01_ArraysSort {

    Integer[] arr;
    Integer[] sorted = new Integer[] { 1, 2, 3, 4, 5, 6 };
    Integer[] reversed = new Integer[] { 6, 5, 4, 3, 2, 1 };

    @BeforeEach
    public void init() {
        arr = new Integer[] { 3, 5, 4, 1, 6, 2 };
    }

    @Test
    // 匿名内部类
    @DisplayName("匿名内部类排序")
    void test01() {
        Arrays.sort(arr);
        Assertions.assertArrayEquals(arr, sorted);
        Arrays.sort(arr, (o1, o2) -> o2 - o1);
        Assertions.assertArrayEquals(arr, reversed);
    }

    @Test
    @DisplayName("自定义方法引用")
    void test02() {

        // 方法引用
        // 1.引用处需要是函数式接口
        // 2.被引用的方法需要已经存在
        // 3.被引用方法的形参和返回值需要跟抽象方法的形参和返回值保持一致
        // 4.被引用方法的功能需要满足当前的要求
        // 表示引用FunctionDemo1类里面的subtraction方法
        // 把这个方法当做抽象方法的方法体

        Arrays.sort(arr, IT01_ArraysSort::subtraction);
        Assertions.assertArrayEquals(arr, reversed);
    }

    @Test
    @DisplayName("方法引用")
    void test03() {
        Arrays.sort(arr, Comparator.comparingInt(o -> o));
        Assertions.assertArrayEquals(arr, sorted);

        Arrays.sort(arr, Comparator.comparingInt(o -> -o));
        Assertions.assertArrayEquals(arr, sorted);

        Arrays.sort(arr, Integer::compare);
        Assertions.assertArrayEquals(arr, sorted);
    }

    // 可以是Java已经写好的，也可以是一些第三方的工具类
    public static int subtraction(int num1, int num2) {
        return num2 - num1;
    }
}
