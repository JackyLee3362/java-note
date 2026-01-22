package edu.note.java.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Class类中用于获取构造方法的方法
 * Constructor<?>[] getConstructors() 返回 public 构造方法对象的数组
 * Constructor<?>[] getDeclaredConstructors() 返回 all 构造方法对象的数组
 * Constructor<T> getConstructor(Class<?>... parameterTypes) 返回 public 构造方法对象
 * Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) 返回 one
 * 构造方法对象
 * 
 * Constructor类中用于创建对象的方法
 * T newInstance(Object... initArgs) 根据指定的构造方法创建对象
 * setAccessible(boolean flag) 设置为true,表示取消访问检查
 * 
 * @author JackyLee
 * @date 2024/11/28 10:53
 **/
public class IT03_Constructor {

    // 获取class字节码文件对象
    Class<Student> clazz = Student.class;

    @Test
    @DisplayName("返回 public / all 构造函数列表")
    void test1() {
        // public
        assertEquals(3, clazz.getConstructors().length);
        // all = public + protected + default + private
        assertEquals(7, clazz.getDeclaredConstructors().length);
    }

    @Test
    @DisplayName("根据签名返回构造函数")
    void test2() throws NoSuchMethodException {
        // 获取 public 构造方法，无法获取 private 的
        assertEquals(Modifier.PUBLIC, clazz.getConstructor(String.class).getModifiers());
        assertThrows(NoSuchMethodException.class, () -> clazz.getConstructor(int.class, int.class));
        // 获取 private 构造方法
        assertEquals(Modifier.PRIVATE, clazz.getDeclaredConstructor(int.class, double.class).getModifiers());
    }

    @Test
    @DisplayName("验证参数")
    void test3() throws Exception {

        Constructor<?> con4 = clazz.getDeclaredConstructor(int.class, double.class);
        assertEquals(con4.getModifiers(), Modifier.PRIVATE);

        Parameter[] params = con4.getParameters();
        assertEquals(params.length, 2);
        assertEquals(params[0].getName(), "arg0");
        assertEquals(params[1].getName(), "arg1");

        // ! 暴力反射：表示临时取消权限校验
        // private 无法直接执行，会抛出异常
        assertThrows(IllegalAccessException.class, () -> con4.newInstance(18, 1.88));
        con4.setAccessible(true);
        assertNotNull((Student) con4.newInstance(18, 1.88));
    }
}