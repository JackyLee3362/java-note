package edu.note.java.reflect;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/27 下午10:06
 */
public class IT01_Reflect {

    @Test
    @DisplayName("反射基础")
    void testBasicUsage() throws Exception {
        // 1. 获取Class对象
        Class<?> clazz = Class.forName("edu.note.java.reflect.Student");
        // 2. 创建对象
        Object obj = clazz.newInstance();
        // 3. 获取方法
        // 3.1 获取所有方法
        Method[] methods = clazz.getMethods();
        // 3.2 获取指定方法
        Method method = clazz.getMethod("setName", String.class);
        // 4. 执行方法
        method.invoke(obj, "jacky");
        Student stu = (Student) obj;
        // 断言
        Assertions.assertEquals("jacky", stu.getName());
    }

}
