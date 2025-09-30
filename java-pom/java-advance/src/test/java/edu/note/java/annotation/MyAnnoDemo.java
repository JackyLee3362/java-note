package edu.note.java.annotation;

import java.lang.reflect.Method;

public class MyAnnoDemo {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("edu.note.java.annotation.User");
        Method[] methods = clazz.getDeclaredMethods();
        User mtd = new User();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(MyLogAnno.class)) {
                method.invoke(mtd);
            }
        }

    }
}
