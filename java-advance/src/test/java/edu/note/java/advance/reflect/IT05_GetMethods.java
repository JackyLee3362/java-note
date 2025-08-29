package edu.note.java.advance.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
    @DisplayName("获取里面所有的方法对象(包含父类中所有的公共方法)")
    void test01() {
        Method[] methods = clazz.getMethods();
        Assertions.assertEquals(11, methods.length);
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    @DisplayName("获取里面所有的方法对象(不能获取父类的，但是可以获取本类中私有的方法)")
    void test02() {
        Method[] methods = clazz.getDeclaredMethods();
        Assertions.assertEquals(2, methods.length);
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    void test()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取指定的单一方法
        Method m = clazz.getDeclaredMethod("eat", String.class);
        System.out.println(m);

        // 获取方法的修饰符
        int modifiers = m.getModifiers();
        System.out.println(modifiers);

        // 获取方法的名字
        String name = m.getName();
        System.out.println(name);

        // 获取方法的形参
        Parameter[] parameters = m.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter);
        }

        // 获取方法的抛出的异常
        Class[] exceptionTypes = m.getExceptionTypes();
        for (Class exceptionType : exceptionTypes) {
            System.out.println(exceptionType);
        }

        // 方法运行
        /*
         * Method类中用于创建对象的方法
         * Object invoke(Object obj, Object... args)：运行方法
         * 参数一：用obj对象调用该方法
         * 参数二：调用方法的传递的参数（如果没有就不写）
         * 返回值：方法的返回值（如果没有就不写）
         */

        Student s = new Student("张三");
        m.setAccessible(true);
        // 参数一s：表示方法的调用者
        // 参数二"汉堡包"：表示在调用方法的时候传递的实际参数
        String result = (String) m.invoke(s, "汉堡包");
        System.out.println(result);

    }
}