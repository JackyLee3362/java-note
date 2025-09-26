package edu.note.java.advance.annotation;

//@SuppressWarnings("all") // 压制警告
public class TestMyAnno {

    @MyAnno(name = "all", age = 12, value = {"123", "456"})
    public String username;
}
