package edu.note.java.io;


import java.io.Serializable;
import lombok.Data;


/*
 *
 * Serializable接口里面是没有抽象方法，标记型接口
 * 一旦实现了这个接口，那么就表示当前的Student类可以被序列化
 * 理解：
 *       一个物品的合格证
 * */

@Data
public class Student implements Serializable {

    // @Serial
    private static final long serialVersionUID = 2252015747540652000L;
    private String name;
    private int age;

    // transient 瞬态关键字
    // 不会把当前属性序列化到本地文件当中
    private transient String address;


    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
