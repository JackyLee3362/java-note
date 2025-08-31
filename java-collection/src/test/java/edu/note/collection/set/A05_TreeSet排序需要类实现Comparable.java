package edu.note.collection.set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

public class A05_TreeSet排序需要类实现Comparable {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * 需求：创建TreeSet集合，并添加3个学生对象
         * 学生对象属性：
         * 姓名，年龄。
         * 要求按照学生的年龄进行排序
         * 同年龄按照姓名字母排列（暂不考虑中文）
         * 同姓名，同年龄认为是同一个人
         * 
         * 方式一：
         * 默认的排序规则/自然排序
         * Student实现Comparable接口，重写里面的抽象方法，再指定比较规则
         */

        // 1.创建三个学生对象
        Student_比较姓名 s1 = new Student_比较姓名("zhangsan", 23);
        Student_比较姓名 s2 = new Student_比较姓名("lisi", 24);
        Student_比较姓名 s3 = new Student_比较姓名("wangwu", 25);
        Student_比较姓名 s4 = new Student_比较姓名("zhaoliu", 26);

        // 2.创建集合对象
        TreeSet<Student_比较姓名> ts = new TreeSet<>();

        // 3.添加元素
        ts.add(s3);
        ts.add(s2);
        ts.add(s1);
        ts.add(s4);

        // 4.打印集合
        System.out.println(ts);

        // TreeSet 底层是红黑树

    }
}