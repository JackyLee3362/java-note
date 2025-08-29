package edu.note.java.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class A04_注解的解析 {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName("day36_05_注解.A03_MyTest");
        Method[] methods = clazz.getDeclaredMethods();
        A03_加上MyTest注解 mtd = new A03_加上MyTest注解();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(mtd);
            }
        }

    }
}
