package edu.note.java.advance.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAnnoParse {

    public static void main(String[] args)
        throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("day36_05_注解.A03_MyTest");
        Method[] methods = clazz.getDeclaredMethods();
        TestMyAnnoV2 mtd = new TestMyAnnoV2();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(MyAnnoV2.class)) {
                method.invoke(mtd);
            }
        }

    }
}
