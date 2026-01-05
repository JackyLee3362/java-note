package edu.note.java.collection.set;

import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-09-30 12:24
 * @desc - 不可以存储重复元素
 *       - 没有索引
 *       - 可以将元素按照规则进行排序
 *       - TreeSet()：根据其元素的自然排序进行排序
 *       - TreeSet(Comparator comparator) ：根据指定的比较器进行排序
 */
public class TreeSetTest {

    @Test
    @DisplayName("")
    void test0() {

        // 创建集合对象
        TreeSet<Integer> ts = new TreeSet<Integer>();

        // 添加元素
        ts.add(10);
        ts.add(40);
        ts.add(30);
        ts.add(50);
        ts.add(20);
        ts.add(30);

        // 遍历集合
        for (Integer i : ts) {
            System.out.println(i);
        }
    }
}
