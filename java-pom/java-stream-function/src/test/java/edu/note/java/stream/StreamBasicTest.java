package edu.note.java.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamBasicTest {

    public static final List<String> list = Arrays.asList("aaa", "bbb", "aa");

    @Test
    @DisplayName("基础操作")
    void test01() {
        // 遍历
        list.forEach(System.out::println);
        // 统计
        long count = list.stream().filter(s -> s.startsWith("a")).count();
        Assertions.assertEquals(2, count);
        // toArray
        String[] arr = list.toArray(new String[10]);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    @DisplayName("过滤")
    void testFilter() {
        // filter 过滤
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

        list.stream()
            .filter(s -> s.startsWith("a"))
            .filter(s -> s.length() == 3)
            .forEach(System.out::println);
    }

    @Test
    void testStreamToList() {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        // List<Integer> collect =
        // list.stream().map(Integer::parseInt).collect(Collectors.toList());
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.stream().map(Integer::parseInt).toArray());
    }


    @Test
    @DisplayName("limit 和 skip")
    void test02() {

        // 最多输出 2 个
        list.stream().limit(2).forEach(System.out::println);
        // 跳过 3 个
        list.stream().skip(3).forEach(System.out::println);
        // 最多输出 6 个，然后跳过 3 个
        list.stream().limit(6).skip(3).forEach(System.out::println);
        // 跳过 3 个，然后输出 6 个
        list.stream().skip(3).limit(3).forEach(System.out::println);

    }

    @Test
    @DisplayName("Distinct(去重) 和 Concat(合并)")
    void test03() {

        List<String> list2 = Arrays.asList("a", "b", "b", "c");

        // distinct 元素去重，依赖(hashCode和equals方法)
        Assertions.assertEquals(3, list2.stream().distinct().count());

        // concat: 合并a和b两个流为一个流
        Assertions.assertEquals(7, Stream.concat(list.stream(), list2.stream()).count());
    }

    @Test
    @DisplayName("list 转换为 map")
    void test04() {
        List<String> list = Arrays.asList("a", "b", "c");
        Map<String, Integer> map = list.stream()
            .collect(Collectors.toMap(s -> s, String::length, (s1, s2) -> s1));
        Assertions.assertEquals(3, map.size());

    }
}
