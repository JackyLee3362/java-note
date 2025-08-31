package edu.note.collection.generical;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class A02_集合定义父类_添加子对象 {
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
