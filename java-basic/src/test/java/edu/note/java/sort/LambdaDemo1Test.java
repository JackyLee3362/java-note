package edu.note.java.sort;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class LambdaDemo1Test {

    @Test
    void test() {

        // method1();
        String[] list = {"123456789", "123", "456", "12345", "abcedfghijk"};
        Arrays.sort(list, (o1, o2) -> o1.length() - o2.length());
        System.out.println(Arrays.toString(list));
    }

    private static void method1() {
        Integer[] list = {1, 2, 0, 3, 4};
        Arrays.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        Arrays.sort(list, (Integer o1, Integer o2) -> {
            return o1 - o2;
        });
        System.out.println(Arrays.toString(list));
    }
}
