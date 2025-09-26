package edu.note.java.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MethodReferenceTest {
    @Test
    @DisplayName("方法引用:new")
    void test01(){

        ArrayList<Student> list = new ArrayList<>();
        // 2.添加元素
        list.add(new Student("foo", 23));
        list.add(new Student("bar", 24));
        // 3.获取姓名并放到数组当中

        String[] arr = list.stream().map(Student::getName).toArray(String[]::new);

        /*
         * String[] arr = list.stream().map(new Function<Student, String>() {
         * 
         * @Override
         * public String apply(Student student) {
         * return student.getName();
         * }
         * }).toArray(String[]::new);
         */

        System.out.println(Arrays.toString(arr));

    }

    @Test
    @DisplayName("方法引用:静态方法")
    void test() {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "1", "2", "3", "4", "5");

        // 2.把他们都变成int类型
        /*
         * list.stream().map(new Function<String, Integer>() {
         *
         * @Override
         * public Integer apply(String s) {
         * int i = Integer.parseInt(s);
         * return i;
         * }
         * }).forEach(s -> System.out.println(s));
         */

        // 1.方法需要已经存在
        // 2.方法的形参和返回值需要跟抽象方法的形参和返回值保持一致
        // 3.方法的功能需要把形参的字符串转换成整数

        list.stream()
            .map(Integer::parseInt)
            .forEach(System.out::println);

    }
    @Test
    @DisplayName("方法引用_成员方法")
    void test01_02(){

        /*
         * 方法引用（引用成员方法）
         * 格式
         * 其他类：其他类对象::方法名
         * 本类：this::方法名(引用处不能是静态方法)
         * 父类：super::方法名(引用处不能是静态方法)
         * 需求：
         * 集合中有一些名字，按照要求过滤数据
         * 数据："张无忌","周芷若","赵敏","张强","张三丰"
         * 要求：只要以张开头，而且名字是3个字的
         *
         */

        // 1.创建集合
        ArrayList<String> list = new ArrayList<>();
        // 2.添加数据
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
        // 3.过滤数据（只要以张开头，而且名字是3个字的）
        // list.stream().filter(s->s.startsWith("张")).filter(s->s.length() ==
        // 3).forEach(s-> System.out.println(s));

        /*
         * list.stream().filter(new Predicate<String>() {
         *
         * @Override
         * public boolean test(String s) {
         * return s.startsWith("张") && s.length() == 3;
         * }
         * }).forEach(s-> System.out.println(s));
         */

        /*
         * StringOperation so = new StringOperation();
         * list.stream().filter(so::stringJudge)
         * .forEach(s-> System.out.println(s));
         */

        // 静态方法中是没有this的
        list.stream().filter(new MethodReferenceTest()::stringJudge)
            .forEach(System.out::println);

    }

    public boolean stringJudge(String s) {
        return s.startsWith("张") && s.length() == 3;
    }
    @Test
    @DisplayName("方法引用_构造方法")
    void test04(){

        /*
         * 方法引用（引用构造方法）
         * 格式
         * 类名::new
         *
         * 目的：
         * 创建这个类的对象
         *
         * 需求：
         * 集合里面存储姓名和年龄，要求封装成Student对象并收集到List集合中
         *
         * 方法引用的规则：
         * 1.需要有函数式接口
         * 2.被引用的方法必须已经存在
         * 3.被引用方法的形参和返回值，需要跟抽象方法的形参返回值保持一致
         * 4.被引用方法的功能需要满足当前的需求
         */

        // 1.创建集合对象
        ArrayList<String> list = new ArrayList<>();
        // 2.添加数据
        Collections.addAll(list, "张无忌,15", "周芷若,14", "赵敏,13", "张强,20", "张三丰,100", "张翠山,40", "张良,35", "王二麻子,37",
            "谢广坤,41");
        // 3.封装成Student对象并收集到List集合中
        // String --> Student
        /*
         * List<Student> newList = list.stream().map(new Function<String, Student>() {
         *
         * @Override
         * public Student apply(String s) {
         * String[] arr = s.split(",");
         * String name = arr[0];
         * int age = Integer.parseInt(arr[1]);
         * return new Student(name, age);
         * }
         * }).collect(Collectors.toList());
         * System.out.println(newList);
         */

        List<Student> newList2 = list.stream().map(Student::new).collect(Collectors.toList());
        System.out.println(newList2);

    }

    static void testStatic() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌,15", "周芷若,14", "赵敏,13", "张强,20", "张三丰,100", "张翠山,40", "张良,35", "王二麻子,37",
            "谢广坤,41");
        List<Student> list1 = list.stream().map(Student::new).collect(Collectors.toList());
    }

    @Test
    @DisplayName("方法引用")
    void test09(){

        /*
         * 需求：
         * 集合中存储一些字符串的数据，比如：张三,23。
         * 收集到Student类型的数组当中
         */
        // 1.创建集合并添加元素
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌,15", "周芷若,14", "赵敏,13", "张强,20", "张三丰,100", "张翠山,40", "张良,35", "王二麻子,37",
            "谢广坤,41");
        // 2.先把字符串变成Student对象，然后再把Student对象收集起来
        Student[] arr = list.stream().map(Student::new).toArray(Student[]::new);
        // 打印数组
        System.out.println(Arrays.toString(arr));

    }

    @Test
    @DisplayName("方法引用_类名引用成员方法")
    void test0x(){

        /*
         * 方法引用（类名引用成员方法）
         * 格式
         * 类名::成员方法
         * 需求：
         * 集合里面一些字符串，要求变成大写后进行输出
         *
         *
         * 方法引用的规则：
         * 1.需要有函数式接口
         * 2.被引用的方法必须已经存在
         * 3.被引用方法的形参，需要跟抽象方法的第二个形参到最后一个形参保持一致，返回值需要保持一致。
         * 4.被引用方法的功能需要满足当前的需求
         *
         * 抽象方法形参的详解：
         * 第一个参数：表示被引用方法的调用者，决定了可以引用哪些类中的方法
         * 在Stream流当中，第一个参数一般都表示流里面的每一个数据。
         * 假设流里面的数据是字符串，那么使用这种方式进行方法引用，只能引用String这个类中的方法
         *
         * 第二个参数到最后一个参数：跟被引用方法的形参保持一致，如果没有第二个参数，说明被引用的方法需要是无参的成员方法
         *
         * 局限性：
         * 不能引用所有类中的成员方法。
         * 是跟抽象方法的第一个参数有关，这个参数是什么类型的，那么就只能引用这个类中的方法。
         *
         */

        // 1.创建集合对象
        ArrayList<String> list = new ArrayList<>();
        // 2.添加数据
        Collections.addAll(list, "aaa", "bbb", "ccc", "ddd");
        // 3.变成大写后进行输出
        // map(String::toUpperCase)
        // 拿着流里面的每一个数据，去调用String类中的toUpperCase方法，方法的返回值就是转换之后的结果。
        list.stream().map(String::toUpperCase).forEach(s -> System.out.println(s));

        // String --> String
        /*
         * list.stream().map(new Function<String, String>() {
         *
         * @Override
         * public String apply(String s) {
         * return s.toUpperCase();
         * }
         * }).forEach(s -> System.out.println(s));
         */

        // test(); // 我写的代码
    }

    static void testGetList() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "aaa", "bbb", "ccc", "ddd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }
    @Test
    @DisplayName("方法引用_数组的构造方法")
    void test0x11(){

        /*
         * 方法引用（数组的构造方法）
         * 格式
         * 数据类型[]::new
         * 目的：
         * 创建一个指定类型的数组
         * 需求：
         * 集合中存储一些整数，收集到数组当中
         *
         * 细节：
         * 数组的类型，需要跟流中数据的类型保持一致。
         *
         */

        // 1.创建集合并添加元素
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);
        // 2.收集到数组当中

        Integer[] arr2 = list.stream().toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr2));

        /*
         * Integer[] arr = list.stream().toArray(new IntFunction<Integer[]>() {
         *
         * @Override
         * public Integer[] apply(int value) {
         * return new Integer[value];
         * }
         * });
         */
        // 3.打印

    }
}
