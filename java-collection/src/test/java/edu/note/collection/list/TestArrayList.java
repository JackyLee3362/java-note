package edu.note.collection.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestArrayList {
    @Test
    @DisplayName("")
    void testBasic01() {

        // 创建 List
        List<String> list = new ArrayList<>();

        // void add(int index,E element) 指定位置插入元素
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // E remove(int index) 删除指定索引处的元素，返回被删除的元素
        String remove = list.remove(0);
        Assertions.assertEquals("aaa", remove);

        // E set(int index,E element) 修改指定索引处的元素，返回被修改的元素
        String result = list.set(0, "ddd");
        Assertions.assertEquals("aaa", result);

        // E get(int index) 返回指定索引处的元素
        String s = list.get(0);
        Assertions.assertEquals("ddd", s);

    }
}
