package edu.note.collection.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
public class IteratorTest {

    static Collection<String> coll = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
    }

    @Test
    @DisplayName("普通遍历")
    void test01() {
        for (int i = 0; i < coll.size(); i++) {
            // ...
            // 不适用 collection,适用 list
        }
    }

    @Test
    @DisplayName("迭代器对象遍历")
    void test02() {
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
    void test03() {
        // 注意点：
        // s其实就是一个第三方变量，在循环的过程中依次表示集合中的每一个数据
        for (String e : coll) {
            e = "qqq"; // 中间改变值并不会
        }

        System.out.println(coll);

    }

    @Test
    @DisplayName("lambda表达式遍历")
    void test04() {
        // 2.利用匿名内部类的形式
        // 底层原理：
        // 其实也会自己遍历集合，依次得到每一个元素
        // 把得到的每一个元素，传递给下面的accept方法

        // lambda表达式
        coll.forEach(System.out::println);
    }
}
