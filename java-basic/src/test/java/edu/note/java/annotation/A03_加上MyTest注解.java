package edu.note.java.annotation;

public class A03_加上MyTest注解 {
    @MyTest
    public void method1(){
        System.out.println("method1");
    }
    @MyTest
    public void method2(){
        System.out.println("method2");
    }
    public void method3(){
        System.out.println("method3");
    }
}
