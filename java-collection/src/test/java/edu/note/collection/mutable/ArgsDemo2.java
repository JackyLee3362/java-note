package edu.note.collection.mutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArgsDemo2 {
    @Test
    @DisplayName("")
    void test01() {
        // 计算n个数据的和
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int sum = getSum(arr);
        System.out.println(sum);
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum = sum + i;
        }
        return sum;
    }

}
