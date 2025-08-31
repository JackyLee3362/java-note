package edu.note.collection.generical;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class A01_不指定类型时默认Object类型 {
    @Test
    @DisplayName("")
    void test01() {
        // 没有泛型的时候，集合默认Object类型
        // 此时可以往集合添加任意的数据类型。

        // 1.创建集合的对象
        ArrayList<Object> list = new ArrayList<>();

        // 2.添加数据
        // list.add(123);
        list.add("aaa");
        // list.add(new Student("zhangsan",123));

        // 3.遍历集合获取里面的每一个元素
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            Object str = it.next();
            // 多态的弊端是不能访问子类的特有功能
            // obj.length();
            // str.length();
            System.out.println(str);
        }

    }
}
