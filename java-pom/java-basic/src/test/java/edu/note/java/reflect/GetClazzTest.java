package edu.note.java.reflect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取class对象的三种方式
 * - Class.forName("全类名");
 * - 类名.class;
 * - 对象.getClass
 * 
 * @author JackyLee
 * @date 2024/11/28 10:54
 **/
public class GetClazzTest {

    @Data
    @AllArgsConstructor
    static public class Student {
        String name;
    }

    @Test
    @DisplayName("测试获取类对象1,最常用包名+类名")
    void test1() throws ClassNotFoundException {
        // 全类名 ： 包名 + 类名
        Class<?> clazz1 = Class.forName("edu.note.java.reflect.Student");

    }

    @Test
    @DisplayName("测试获取类对象2,使用场景:一般参数传递时使用")
    void test_02() {
        // given:
        Class<Student> clazz2 = Student.class;
    }

    @Test
    @DisplayName("测试获取类3,从已对象中获取类对象")
    void test_03() {
        // given:
        // 3.第三种方式
        // 从已创建的对象获取类对象
        Student s = new Student("Foo");
        Class<? extends Student> clazz3 = s.getClass();

    }
}