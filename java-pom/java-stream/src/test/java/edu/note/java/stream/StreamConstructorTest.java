package edu.note.java.stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamConstructorTest {

    // 单列集合 default Stream<E> stream() Collection中的默认方法
    // 数组 public static <T> Stream<T> stream(T[] array) Arrays工具类中的静态方法
    // 一堆零散数据 public static<T> Stream<T> of(T... values) Stream接口中的静态方法
    @Test
    @DisplayName("数组集合")
    void test01() {

        // 1.创建数组
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        String[] arr2 = {"a", "b", "c"};

        // 2.获取stream流
        Arrays.stream(arr1).forEach(System.out::println);
        Arrays.stream(arr2).forEach(System.out::println);

        // 注意：
        // Stream接口中静态方法of的细节
        // 方法的形参是一个可变参数，可以传递一堆零散的数据，也可以传递数组
        // 但是数组必须是引用数据类型的，如果传递基本数据类型，是会把整个数组当做一个元素，放到Stream当中。
        Stream.of(arr1).forEach(System.out::println);//[I@41629346
        Stream.of(arr2).forEach(System.out::println);
        // 直接创建
        Stream.of(1, 2, 3, 4, 5).forEach(System.out::println);
        Stream.of("a", "b", "c", "d", "e").forEach(System.out::println);

    }

    @Test
    @DisplayName("单列集合")
    void test02() {

        // 1.单列集合获取Stream流
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "d", "e");

        // 获取到一条流水线，并把集合中的数据放到流水线上
        Stream<String> stream = list.stream();

        stream.forEach(System.out::println);
        list.forEach(System.out::println);

    }

    @Test
    @DisplayName("map stream")
    void test03() {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("a", 96);
        hm.put("b", 97);
        hm.put("c", 98);
        hm.put("d", 99);

        // 1.第一种获取stream流
        hm.keySet().forEach(System.out::println);

        // 2.第二种获取stream流
        hm.entrySet().forEach(System.out::println);
    }

}







