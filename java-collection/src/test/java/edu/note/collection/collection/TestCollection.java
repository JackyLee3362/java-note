package edu.note.collection.collection;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCollection {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * public boolean add(E e) 添加
         * public void clear() 清空
         * public boolean remove(E e) 删除
         * public boolean contains(Object obj) 判断是否包含
         * public boolean isEmpty() 判断是否为空
         * public int size() 集合长度
         */
        Collection<String> coll = new ArrayList<>();

        // 1.添加元素
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");

        // 2.清空
        coll.clear();
        Assertions.assertEquals(new ArrayList<String>(), coll);

        // 3.删除
        // 因为Collection里面定义的是共性的方法，所以此时不能通过索引进行删除。只能通过元素的对象进行删除。
        // 返回值，删除成功返回true，删除失败返回false
        // 如果要删除的元素不存在，就会删除失败
        Assertions.assertEquals(false, coll.remove("aaa"));

        // 4.判断是否包含某元素
        // 底层是依赖equals方法进行判断是否存在的
        // 如果集合中存储的是自定义对象，也想通过contains方法来判断是否包含，那么在javabean类中，【一定】要重写equals方法。
        boolean result1 = coll.contains("bbb");
        Assertions.assertEquals(false, result1);

        // 5.判断集合是否为空
        boolean result2 = coll.isEmpty();
        Assertions.assertEquals(true, result2);

        // 6.获取集合的长度
        Assertions.assertEquals(0, coll.size());

    }
}
