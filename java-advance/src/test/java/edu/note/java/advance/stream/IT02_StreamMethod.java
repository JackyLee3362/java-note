package edu.note.java.advance.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
    filter              过滤
    limit               获取前几个元素
    skip                跳过前几个元素

    注意1：中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式编程
    注意2：修改Stream流中的数据，不会影响原来集合或者数组中的数据
*/
public class IT02_StreamMethod {

    ArrayList<String> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");
    }

    @Test
    @DisplayName("Filter Demo")
    public void test01() {

        // filter 过滤
        list.stream().filter(s -> s.startsWith("张")).forEach(System.out::println);

        list.stream()
            .filter(s -> s.startsWith("张"))
            .filter(s -> s.length() == 3)
            .forEach(System.out::println);

        System.out.println(list);
    }

    //  limit 获取前几个元素
    //  skip 跳过前几个元素
    @Test
    @DisplayName("Limit And Skip")
    public void test02() {

        list.stream().limit(3).forEach(System.out::println);
        list.stream().skip(4).forEach(System.out::println);

        list.stream().limit(6).skip(3).forEach(System.out::println);

        list.stream().skip(3).limit(3).forEach(System.out::println);

    }

    /*
        distinct 元素去重，依赖(hashCode和equals方法)
        concat 合并a和b两个流为一个流

        注意1：中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式编程
        注意2：修改Stream流中的数据，不会影响原来集合或者数组中的数据
    */
    @Test
    @DisplayName("Distinct And Concat")
    public void test03() {

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "周芷若", "赵敏");

        // distinct 元素去重，依赖(hashCode和equals方法)
        list.stream().distinct().forEach(System.out::println);

        Stream.concat(list.stream(), list2.stream()).forEach(System.out::println);
    }

    /*
       map 转换流中的数据类型

       注意1：中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式编程
       注意2：修改Stream流中的数据，不会影响原来集合或者数组中的数据
   */
    @Test
    // 需求：只获取里面的年龄并进行打印
    @DisplayName("map")
    public void test04() {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-15", "周芷若-14", "赵敏-13", "张强-20", "张三丰-100", "张翠山-40", "张良-35",
            "王二麻子-37", "谢广坤-41");
        list.stream()
            .map(s -> Integer.parseInt(s.split("-")[1]))
            .forEach(System.out::println);
    }
}
