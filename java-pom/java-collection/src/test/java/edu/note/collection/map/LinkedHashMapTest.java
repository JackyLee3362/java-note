package edu.note.collection.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LinkedHashMapTest {
    @Test
    @DisplayName("")
    void test01() {
        /*
         * LinkedHashMap:
         * 由键决定：
         * 有序、不重复、无索引。
         * 有序：
         * 保证存储和取出的顺序一致
         * 原理：
         * 底层数据结构是依然哈希表，只是每个键值对元素又额外的多了一个双链表的机制记录存储的顺序。
         */

        Map<Integer/* id */, String/* 省份 */> hm = new LinkedHashMap<>();

        // 添加元素
        hm.put(1, "江苏");
        hm.put(2, "浙江");
        hm.put(3, "福建");
        hm.put(4, "山东");

        assertEquals(4, hm.size());
        assertEquals("江苏", hm.get(1));

    }
}
