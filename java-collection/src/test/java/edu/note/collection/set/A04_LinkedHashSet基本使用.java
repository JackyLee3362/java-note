package edu.note.collection.set;

import java.util.LinkedHashSet;

public class A04_LinkedHashSet基本使用 {
    public static void main(String[] args) {
        //1.创建4个学生对象
        Student_比较姓名 s1 = new Student_比较姓名("zhangsan",23);
        Student_比较姓名 s2 = new Student_比较姓名("lisi",24);
        Student_比较姓名 s3 = new Student_比较姓名("wangwu",25);
        Student_比较姓名 s4 = new Student_比较姓名("zhangsan",23);


        //2.创建集合的对象
        LinkedHashSet<Student_比较姓名> lhs = new LinkedHashSet<>();


        //3.添加元素
        System.out.println(lhs.add(s3));
        System.out.println(lhs.add(s2));
        System.out.println(lhs.add(s1));
        System.out.println(lhs.add(s4));

        //4.打印集合
        System.out.println(lhs);
    }
}
