package edu.note.java.advance.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Class类中用于获取构造方法的方法
 * Constructor<?>[] getConstructors() 返回所有公共构造方法对象的数组
 * Constructor<?>[] getDeclaredConstructors() 返回所有构造方法对象的数组
 * Constructor<T> getConstructor(Class<?>... parameterTypes) 返回单个公共构造方法对象
 * Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) 返回单个构造方法对象
 * 
 * Constructor类中用于创建对象的方法
 * T newInstance(Object... initArgs) 根据指定的构造方法创建对象
 * setAccessible(boolean flag) 设置为true,表示取消访问检查
 * 
 * @author JackyLee
 * @date 2024/11/28 10:53
 **/
public class IT03_GetConstructor {

    // 获取class字节码文件对象
    Class<Student> clazz = Student.class;

    @Test
    @DisplayName("返回【公共】构造函数列表")
    void test1() {
        // 【公共】
        Assertions.assertEquals(2, clazz.getConstructors().length);
        // 【公共、默认、保护、私有】
        Assertions.assertEquals(7, clazz.getDeclaredConstructors().length);
    }

    @Test
    @DisplayName("根据签名返回构造函数")
    void test2() throws NoSuchMethodException {
        // 获取【公共】构造方法，无法获取【私有】的
        Assertions.assertNotNull(clazz.getConstructor(String.class));
        Assertions.assertThrows(NoSuchMethodException.class, () -> {
            clazz.getConstructor();
        });
        // 获取【私有】构造方法
        Assertions.assertNotNull(clazz.getDeclaredConstructor());
    }

    @Test
    void test3()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor con4 = clazz.getDeclaredConstructor(String.class, int.class, double.class, char.class);
        Assertions.assertEquals(con4.getModifiers(), Modifier.PUBLIC);

        Parameter[] params = con4.getParameters();
        Assertions.assertEquals(params.length, 4);
        Assertions.assertEquals(params[0].getName(), "arg0");
        Assertions.assertEquals(params[1].getName(), "arg1");

        // ! 暴力反射：表示临时取消权限校验
        con4.setAccessible(true);
        Student stu = (Student) con4.newInstance("张三", 18, 1.88, '男');
    }
}