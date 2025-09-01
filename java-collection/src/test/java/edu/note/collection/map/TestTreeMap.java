package edu.note.collection.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestTreeMap {
    @Test
    @DisplayName("TreeMap集合的基本应用")
    void test01() {
        Map<Integer, String> m1 = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // o1:当前要添加的元素
                // o2：表示已经在红黑树中存在的元素
                return o2 - o1;
            }
        });

        // 2.添加元素
        m1.put(5, "浙江");
        m1.put(4, "江苏");
        m1.put(3, "上海");
        m1.put(2, "北京");
        m1.put(1, "广州");

        Map<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> (Integer) o));

    }
}
