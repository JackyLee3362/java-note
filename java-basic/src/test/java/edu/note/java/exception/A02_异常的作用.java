package edu.note.java.exception;

public class A02_异常的作用 {
    public static void main(String[] args) {
        /*
            异常作用一：异常是用来查询bug的关键参考信息
            异常作用二：异常可以作为方法内部的一种特殊返回值，以便通知调用者底层的执行情况
            异常输出方式是以调用栈的方式输出

            最底下是入口,最上面是真正的发生错误的地方
        */

        Student[] arr = new Student[3];// null null null

        String name = arr[0].getName();
        System.out.println(name);
    }
}
