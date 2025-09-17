package edu.note.java.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/30 下午10:42
 */
public class IT00_ListInit {

    @Test
    @DisplayName("初始化 List 的方式")
    void test01() {
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

}
