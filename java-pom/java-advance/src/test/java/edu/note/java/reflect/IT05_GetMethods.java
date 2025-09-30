package edu.note.java.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Class类中用于获取成员方法的方法
 * Method[] getMethods()：返回所有公共成员方法对象的数组，包括继承的
 * Method[] getDeclaredMethods()：返回所有成员方法对象的数组，不包括继承的
 * Method getMethod(String name, Class<?>... parameterTypes) ：返回单个公共成员方法对象
 * Method getDeclaredMethod(String name, Class<?>... parameterTypes)：返回单个成员方法对象
 * 
 * 
 * Method类中用于创建对象的方法
 * Object invoke(Object obj, Object... args)：运行方法
 * 参数一：用obj对象调用该方法
 * 参数二：调用方法的传递的参数（如果没有就不写）
 * 返回值：方法的返回值（如果没有就不写）
 * 
 * 获取方法的修饰符
 * 获取方法的名字
 * 获取方法的形参
 * 获取方法的返回值
 * 获取方法的抛出的异常
 * 
 * @author JackyLee
 * @date 2024/11/28 10:56
 **/
public class IT05_GetMethods {

    // 1. 获取class字节码文件对象
    Class<Student> clazz = Student.class;

    @Test
    @DisplayName("获取本类 public +父类 public 方法")
    void test01() {
        Method[] methods = clazz.getMethods();
        Assertions.assertEquals(11, methods.length);
    }

    @Test
    @DisplayName("获取本类 all + 父类 public 方法")
    void test02() {
        Method[] methods = clazz.getDeclaredMethods();
        Assertions.assertEquals(2, methods.length);
    }

    @Test
    void test03() throws Exception {
        // 获取指定的单一方法
        Method m = clazz.getDeclaredMethod("eat", String.class);

        // 获取方法的修饰符
        Assertions.assertEquals(Modifier.PUBLIC, m.getModifiers());

        // 获取方法的名字
        Assertions.assertEquals("eat", m.getName());

        // 获取方法的形参
        Assertions.assertEquals(String.class, m.getParameters()[0].getType());

        // 获取方法的抛出的异常
        Assertions.assertEquals(Exception.class, m.getExceptionTypes()[0]);

        // 方法运行
        Student s = new Student("Foo");
        Assertions.assertEquals("Bar", (String) m.invoke(s, "Bar"));

    }
}