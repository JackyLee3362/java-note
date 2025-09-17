package edu.note.java.advance.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
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
public class IT04_GetParams {

    // 1.获取class字节码文件的对象
    Class<Student> clazz = Student.class;

    @Test
    @DisplayName("获取成员变量")
    void test01() {
        Field[] fields = clazz.getDeclaredFields();
        Assertions.assertEquals(4, fields.length);
    }

    @Test
    @DisplayName("获取单个成员变量")
    void test02() throws NoSuchFieldException, IllegalAccessException {

        // 获取单个的成员变量
        Field name = clazz.getDeclaredField("name");
        Assertions.assertEquals(name.getName(), "name");
        // 获取权限修饰符
        int modifiers = name.getModifiers();
        Assertions.assertEquals(modifiers, Modifier.PRIVATE);
        // 获取成员变量的名字

        // 获取成员变量的数据类型
        Class<?> type = name.getType();
        Assertions.assertEquals(type, String.class);

        // 获取成员变量记录的值
        Student s = new Student("张三");
        name.setAccessible(true);
        String value = (String) name.get(s);
        Assertions.assertEquals(value, "张三");

        // 修改对象里面记录的值
        name.set(s, "李四");
        Assertions.assertEquals(s.getName(), "李四");

    }

}