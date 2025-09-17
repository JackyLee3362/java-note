package edu.note.collection.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestIterator {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * Collection系列集合三种通用的遍历方式：
         * 1.迭代器遍历
         * 2.增强for遍历
         * 3.lambda表达式遍历
         * 
         * 
         * 迭代器遍历相关的三个方法：
         * Iterator<E> iterator() ：获取一个迭代器对象
         * boolean hasNext() ：判断当前指向的位置是否有元素
         * E next() ：获取当前指向的元素并移动指针
         */

        // 1.创建集合并添加元素
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");

        // 2.获取迭代器对象
        // 迭代器就好比是一个箭头，默认指向集合的0索引处
        Iterator<String> it = coll.iterator();
        // 3.利用循环不断的去获取集合中的每一个元素
        while (it.hasNext()) {
            // 4.next方法的两件事情：获取元素并移动指针
            String str = it.next();
            System.out.println(str);
        }

    }

    @Test
    @DisplayName("增强 for 遍历")
    void test02() {
        /*
         * Collection系列集合三种通用的遍历方式：
         * 1.迭代器遍历
         * 2.增强for遍历
         * 3.lambda表达式遍历
         * 
         * 增强for格式：
         * for(数据类型 变量名: 集合/数组){
         * 
         * }
         */

        // 1.创建集合并添加元素
        Collection<String> coll = new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");

        // 2.利用增强for进行遍历
        // 注意点：
        // s其实就是一个第三方变量，在循环的过程中依次表示集合中的每一个数据
        for (String s : coll) {
            s = "qqq"; // 中间改变值并不会
        }

        System.out.println(coll);// zhangsan lisi wangwu

    }

    @Test
    @DisplayName("")
    void test03() {
        /*
         * Collection系列集合三种通用的遍历方式：
         * 1.迭代器遍历
         * 2.增强for遍历
         * 3.lambda表达式遍历
         * 
         * lambda表达式遍历：
         * default void forEach(Consumer<? super T> action):
         */

        // 1.创建集合并添加元素
        Collection<String> coll = new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");

        // 2.利用匿名内部类的形式
        // 底层原理：
        // 其实也会自己遍历集合，依次得到每一个元素
        // 把得到的每一个元素，传递给下面的accept方法
        // s依次表示集合中的每一个数据
        coll.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        // lambda表达式
        coll.forEach(System.out::println);

    }
}
