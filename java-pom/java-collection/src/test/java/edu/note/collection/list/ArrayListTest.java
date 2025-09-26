package edu.note.collection.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArrayListTest {

    @Test
    @DisplayName("")
    void testBasic01() {

        // 创建 List
        List<String> list = new ArrayList<>();

        // void add(int index,E element) 指定位置插入元素
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // E remove(int index) 删除指定索引处的元素，返回被删除的元素
        String remove = list.remove(0);
        Assertions.assertEquals("aaa", remove);

        // E set(int index,E element) 修改指定索引处的元素，返回被修改的元素
        String result = list.set(0, "ddd");
        Assertions.assertEquals("bbb", result);

        // E get(int index) 返回指定索引处的元素
        String s = list.get(0);
        Assertions.assertEquals("ddd", s);

    }

    @Test
    @DisplayName("遍历方式")
    void testBasic02() {
        /*
         * List系列集合的五种遍历方式：
         * 1.迭代器
         * 2.列表迭代器
         * 3.增强for
         * 4.Lambda表达式
         * 5.普通for循环
         */

        // 创建集合并添加元素
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // 1.迭代器
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            // ...
        }

        // 2.增强for
        // 下面的变量s，其实就是一个第三方的变量而已。
        // 在循环的过程中，依次表示集合中的每一个元素

        for (String s : list) {
            // ...
        }

        // 3.Lambda表达式 直接 / 方法引用
        list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);

        // 4.普通for循环
        // size方法跟get方法还有循环结合的方式，利用索引获取到集合中的每一个元素

        for (int i = 0; i < list.size(); i++) {
            // ...
        }

        // 5.列表迭代器
        // 获取一个列表迭代器的对象，里面的指针默认指向0索引
        // 额外添加了一个方法：在遍历的过程中，可以添加元素
        ListIterator<String> its = list.listIterator();
        while (its.hasNext()) {
            String str = its.next();
            if ("bbb".equals(str)) {
                // qqq
                its.add("qqq");
            }
        }
        Assertions.assertEquals(4, list.size());

    }

    @Test
    void test011() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(5);
        a.add(2);
        a.add(3);

        a.sort((o1, o2) -> o1 - o2);
        a.stream().forEach(System.out::println);
    }

    @Test
    void test021() {
        int[] numbers = {1, 2, 3, 4, 5}; // 定义一个 int 类型的数组

        // 将数组强制转换为 Object 类型
        Object obj = (Object) numbers;

        // 使用 Object 类型引用调用 Object 类的方法
        System.out.println(obj.getClass()); // 输出结果：class [I，表示它是一个 int 类型的数组

        // 尝试使用 Object 类型引用调用数组的特定方法
        // obj.length(); // 这行代码会导致编译错误，因为 Object 类没有 length() 方法

        // 将 Object 类型引用转回数组类型
        int[] newArray = (int[]) obj;

        // 使用数组类型引用调用数组的特定方法
        int arrayLength = newArray.length;
        System.out.println("数组的长度：" + arrayLength); // 输出结果：数组的长度：5

        // todo: Arrays.copyOf 中，为什么需要强制转换
        // (Object)newType == (Object)Object[].class
        Integer[] a = {1, 2, 3};
        System.out.println(a);
        System.out.println(Integer[].class);
        System.out.println((Object) Integer[].class);
    }
    @Test
    @DisplayName("初始化 List 的方式")
    void testInitList() {
        // 1. 直接初始化
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(list1);
        // 2. Arrays 工具类（不可变）
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        System.out.println(list2);
        // 3. Collections 工具类（不可变）
        List<Integer> list3 = Collections.nCopies(3, 1);
        System.out.println(list3);
        // 4. 匿名内部类（不可变）
        // TODO NOT JDK8
        // List<Integer> list4 = new ArrayList<>() {
        // {
        // add(1);
        // add(2);
        // add(3);
        // }
        // };
        // System.out.println(list4);
        // 5. Stream（不可变）
        // TODO NOT JDK8
        // List<Integer> list5 = Stream.of(1, 2, 3).toList();
        // System.out.println(list5);
        // 6. 使用 of（jdk9）
        // TODO JDK9
        // List<Integer> list6 = List.of(1, 2, 3);
        // System.out.println(list6);
    }
    @Test
    @DisplayName("测试 List.of")
    void test() {
        // List.of 是 Java9 引入的一个静态工厂方法，用于创建不可变的List集合

        // 创建完毕后，无法修改
        // List<String> list = List.of("foo", "bar", "baz");

        // Iterator<String> it = list.iterator();
        // while(it.hasNext()){
        // String s = it.next();
        // System.out.println(s);
        // }

        // for (int i = 0; i < list.size(); i++) {
        // String s = list.get(i);
        // System.out.println(s);
        // }

        // 无法操作
        // list.remove("李四");
        // list.add("aaa");
        // list.set(0,"aaa");
    }
}
