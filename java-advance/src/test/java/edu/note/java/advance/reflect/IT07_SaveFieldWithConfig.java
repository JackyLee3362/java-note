package edu.note.java.advance.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author JackyLee
 * @date 2024/11/28 10:58
 **/
public class IT07_SaveFieldWithConfig {

    @Test
    @DisplayName("应用_和配置文件结合创建对象")
    public void test01() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
        InstantiationException, IllegalAccessException {
        /*
         * 反射可以跟配置文件结合的方式，动态的创建对象，并调用方法
         */

        // 1.读取配置文件中的信息
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("prop.properties");
        prop.load(fis);
        fis.close();

        // 2.获取全类名和方法名
        String className = (String) prop.get("classname");
        String methodName = (String) prop.get("method");

        System.out.println(className);
        System.out.println(methodName);

        // 3.利用反射创建对象并运行方法
        Class<?> clazz = Class.forName(className);

        // 获取构造方法
        Constructor<?> con = clazz.getDeclaredConstructor();
        Object o = con.newInstance();

        // 获取成员方法并运行
        Method method = clazz.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(o);

    }
}