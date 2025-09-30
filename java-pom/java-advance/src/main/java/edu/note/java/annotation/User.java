package edu.note.java.annotation;

//@SuppressWarnings("all") // 压制警告
public class User {

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
