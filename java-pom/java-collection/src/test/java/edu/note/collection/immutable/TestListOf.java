package edu.note.collection.immutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// List.of 是 Java9 引入的一个静态工厂方法，用于创建不可变的List集合
public class TestListOf {
    @Test
    @DisplayName("")
    void test() {

        /*
         * 创建不可变的List集合
         * "张三", "李四", "王五", "赵六"
         */

        // //一旦创建完毕之后，是无法进行修改的，在下面的代码中，只能进行查询操作
        // List<String> list = List.of("张三", "李四", "王五", "赵六");

        // System.out.println(list.get(0));
        // System.out.println(list.get(1));
        // System.out.println(list.get(2));
        // System.out.println(list.get(3));

        // for (String s : list) {
        // System.out.println(s);
        // }

        // Iterator<String> it = list.iterator();
        // while(it.hasNext()){
        // String s = it.next();
        // System.out.println(s);
        // }

        // for (int i = 0; i < list.size(); i++) {
        // String s = list.get(i);
        // System.out.println(s);
        // }

        // //list.remove("李四");
        // //list.add("aaa");
        // list.set(0,"aaa");
    }
}
