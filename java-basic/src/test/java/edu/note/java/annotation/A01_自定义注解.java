package edu.note.java.annotation;

//@SuppressWarnings("all") // 压制警告
public class A01_自定义注解 {
    @MyAnno(name = "all", age = 12, value = {"123", "456"})
    public String username;
}
