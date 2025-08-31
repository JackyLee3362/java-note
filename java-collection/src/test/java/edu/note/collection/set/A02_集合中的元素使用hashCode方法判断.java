package edu.note.collection.set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class A02_集合中的元素使用hashCode方法判断 {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * 哈希值：
         * 对象的整数表现形式
         * 1. 如果没有重写hashCode方法，不同对象计算出的哈希值是不同的
         * 2. 如果已经重写hashcode方法，不同的对象只要属性值相同，计算出的哈希值就是一样的
         * 3. 但是在小部分情况下，不同的属性值或者不同的地址值计算出来的哈希值也有可能一样。（哈希碰撞）
         * 
         */

        // 1.创建对象
        Student_比较姓名 s1 = new Student_比较姓名("zhangsan", 23);
        Student_比较姓名 s2 = new Student_比较姓名("zhangsan", 23);

        // 2.如果没有重写hashCode方法，不同对象计算出的哈希值是不同的
        // 如果已经重写hashcode方法，不同的对象只要属性值相同，计算出的哈希值就是一样的
        System.out.println(s1.hashCode());// -1461067292
        System.out.println(s2.hashCode());// -1461067292

        // 在小部分情况下，不同的属性值或者不同的地址值计算出来的哈希值也有可能一样。
        // 哈希碰撞
        System.out.println("abc".hashCode());// 96354
        System.out.println("acD".hashCode());// 96354

    }
}
