package edu.note.java.exception;

public class A05_算术异常 {
    public static void main(String[] args) {
        /*
        JVM默认处理异常的方式：
            1. 把异常的名称，异常原因及异常出现的位置等信息输出在了控制台
            2. 程序停止执行，异常下面的代码不会再执行了
        */

        System.out.println(2/0);//算术异常 ArithmeticException
    }
}
