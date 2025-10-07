package edu.note.java.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;

class StreamDemo {

  public static void test1() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++)
      list.add(i);

    int res = list.stream().reduce(0, (a, b) -> a + b);
    int res2 = list.stream().reduce(0, Integer::sum);
    System.out.println(res);
    System.out.println(res2);
  }

  public static void test2() {
    // 如何将数组转化为stream
    int[] a = { 1, 2, 3 };
    // Stream<Integer> stream = Arrays.stream(a); // 这个是不行的
    IntStream stream = Arrays.stream(a); // 这个是可以的
    int max_a = Arrays.stream(a).max().getAsInt();
    int sum_a = Arrays.stream(a).sum();
    System.out.println(max_a);
    System.out.println(sum_a);
  }

  public static void test3() {
    String s = "123 456 789";
    String[] split = s.split(" ");
    Stream<String> stream = Arrays.stream(split);
    // 两种写法都可以（lambda和引用）
    stream.forEach(x -> System.out.println(x));
    stream.forEach(System.out::println);
  }

  public static void test4() {
    int[] a = { 4, 1, 2, 3, 5 };
    IntStream stream = Arrays.stream(a).filter(x -> x > 2);
    stream.forEach(System.out::println);
  }

  public static void main(String[] args) {
    test4();
  }

}