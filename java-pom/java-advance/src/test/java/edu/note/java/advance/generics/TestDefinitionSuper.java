package edu.note.java.advance.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// ## P13-3 -3.3 Generics
//
// ```java
// public void f1(List<? extends Fruit> fruits){
//     Fruit fruit = fruits.get(0);
//     // fruits.add(fruit); // 报错，无法往里塞，因为我们找不到CAP#1及其子类
// }
//
// public void f2(List<? super Fruit> fruits){
//     // Fruit fruit = fruits.get(0); // 报错，无法从中读取，因为fruits中存储的可能是Object或者别的，不一定与Fruit类兼容
//     fruits.add(new Fruit());
// }
// ```
public class TestDefinitionSuper {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * 泛型不具备继承性，但是数据具备继承性
         */

        // 创建集合的对象
        ArrayList<Grandpa> list1 = new ArrayList<>();
        ArrayList<Father> list2 = new ArrayList<>();
        ArrayList<Son> list3 = new ArrayList<>();

        // 调用method方法
        // method(list1);
        // method(list2);
        // method(list3);

        list1.add(new Grandpa());
        list1.add(new Father());
        list1.add(new Son());

    }

    /*
     * 此时，泛型里面写的是什么类型，那么只能传递什么类型的数据。
     */
    public static void method(ArrayList<Grandpa> list) {

    }

}
