package edu.note.java.advance.annotation;

public class TestMyAnnoV2 {

    @MyAnnoV2
    public void method1() {
        System.out.println("method1");
    }

    @MyAnnoV2
    public void method2() {
        System.out.println("method2");
    }

    public void method3() {
        System.out.println("method3");
    }
}
