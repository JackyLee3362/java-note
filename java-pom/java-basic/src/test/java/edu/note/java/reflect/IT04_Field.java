package edu.note.java.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Class类中用于获取成员变量的方法
 * Field[] getFields()： 返回所有公共成员变量对象的数组
 * Field[] getDeclaredFields()： 返回所有成员变量对象的数组
 * Field getField(String name)： 返回单个公共成员变量对象
 * Field getDeclaredField(String name)：返回单个成员变量对象
 * 
 * Field类中用于创建对象的方法
 * void set(Object obj, Object value)：赋值
 * Object get(Object obj) 获取值
 * 
 * @author JackyLee
 * @date 2024/11/28 10:56
 **/
public class IT04_Field {

    // 1.获取class字节码文件的对象
    Class<Student> clazz = Student.class;

    @Test
    @DisplayName("获取字段")
    void test01() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        // 获取所有 public 成员变量
        Field[] fields = clazz.getDeclaredFields();
        assertEquals(4, fields.length);
        // 获取单个字段
        Field name = clazz.getDeclaredField("name");
        assertEquals(name.getName(), "name");
        // 获取权限修饰符
        int modifiers = name.getModifiers();
        assertEquals(modifiers, Modifier.PRIVATE);
        // 获取成员变量的名字

        // 获取成员变量的数据类型
        Class<?> type = name.getType();
        assertEquals(type, String.class);

        // 获取成员变量记录的值
        Student s = new Student("Foo");
        name.setAccessible(true);
        String value = (String) name.get(s);
        assertEquals(value, "Foo");

        // 修改对象里面记录的值
        name.set(s, "Bar");
        assertEquals(s.getName(), "Bar");
    }

    @Test
    @DisplayName("修改字段")
    void test03() throws NoSuchFieldException, IllegalAccessException {

        // 获取单个的成员变量
        Field name = clazz.getDeclaredField("name");
        assertEquals(name.getName(), "name");

        // 获取成员变量记录的值
        Student s = new Student("Foo");
        name.setAccessible(true);
        String value = (String) name.get(s);
        assertEquals(value, "Foo");

        // 修改对象里面记录的值
        name.set(s, "Bar");
        assertEquals(s.getName(), "Bar");

    }

}