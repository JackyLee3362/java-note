package edu.note.java.advance.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestGeneric {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * 需求：
         * 定义一个方法，形参是一个集合，但是集合中的数据类型不确定。
         */

        // 创建集合的对象
        ArrayList<Grandpa> list1 = new ArrayList<>();
        ArrayList<Father> list2 = new ArrayList<>();
        ArrayList<Son> list3 = new ArrayList<>();

        ArrayList<Student2> list4 = new ArrayList<>();

        method(list1);
        method(list2);
        // method(list3);
        // method(list4);

    }

    /*
     *
     * 此时我们就可以使用泛型的通配符：
     * ?: 表示不确定的类型
     * 也可以进行类型的限定
     * ? extends E : 表示 E 或者 E 所有的子类类型
     * ? super E : 表示 E 或者 E 所有的父类类型
     *
     */
    public static void method(ArrayList<? super Father> list) {

    }
}

class Grandpa {
}

class Father extends Grandpa {
}

class Son extends Father {
}

class Student2 {
}