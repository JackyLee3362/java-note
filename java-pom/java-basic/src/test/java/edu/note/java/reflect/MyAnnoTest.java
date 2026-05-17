package edu.note.java.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.annotation.MyAnno;
import edu.note.java.annotation.MyLogAnno;

public class MyAnnoTest {

    @Test
    @DisplayName("测试")
    void test_() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // given:
        Class<?> clazz = MyAnnoTest.class;
        Method[] methods = clazz.getDeclaredMethods();

        // when:

        // then:

        User mtd = new User();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(MyLogAnno.class)) {
                method.invoke(mtd);
            }
        }
    }

    static public class User {

        @MyAnno(name = "all", age = 12, value = { "123", "456" })
        public String username;

        @MyLogAnno
        public void method1() {
            System.out.println("method1");
        }

        @MyLogAnno
        public void method2() {
            System.out.println("method2");
        }

        public void method3() {
            System.out.println("method3");
        }
    }

}
