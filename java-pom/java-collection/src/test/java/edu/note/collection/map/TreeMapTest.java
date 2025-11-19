package edu.note.collection.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {

    @Test
    @DisplayName("TreeMap集合的基本应用")
    void test01() {
        // 写法1
        // o1:当前要添加的元素
        // o2：表示已经在红黑树中存在的元素
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
        // 写法2
        Comparator<Integer> comparator2 = Integer::compareTo;
        // 写法3
        Comparator<Integer> comparator3 = Comparator.reverseOrder();
        // 写法4
        Comparator<Integer> comparator4 = Comparator.naturalOrder();
        Map<Integer, String> map = new TreeMap<>(comparator);
        // 2.添加元素
        map.put(5, "浙江");
        map.put(4, "江苏");
        map.put(3, "上海");
        map.put(2, "北京");
        map.put(1, "广州");

        System.out.println(map);
    }
    
    @Test
    @DisplayName("测试")
    void test02() {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();
        hashMap.put("a", "A");
        hashMap.put("b", "B");

        treeMap.put("a", "A");
        treeMap.put("b", "B");
        
        System.out.println(hashMap);
        System.out.println(treeMap);
    }
}
