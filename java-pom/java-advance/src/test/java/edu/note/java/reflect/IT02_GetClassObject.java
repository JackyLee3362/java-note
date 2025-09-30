package edu.note.java.reflect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 获取class对象的三种方式
 * - Class.forName("全类名");
 * - 类名.class;
 * - 对象.getClass
 * 
 * @author JackyLee
 * @date 2024/11/28 10:54
 **/
public class IT02_GetClassObject {

    @Test
    void test1() throws ClassNotFoundException {
        // 1. 第一种方式（最常用）
        // 全类名 ： 包名 + 类名
        Class<?> clazz1 = Class.forName("edu.note.java.reflect.Student");

        // 2. 第二种方式（一般参数传递时使用）
        Class<Student> clazz2 = Student.class;

        // 3.第三种方式
        // 从已创建的对象获取类对象
        Student s = new Student("Foo");
        Class<? extends Student> clazz3 = s.getClass();

        Assertions.assertEquals(clazz1, clazz2);
        Assertions.assertEquals(clazz2, clazz3);
    }
}