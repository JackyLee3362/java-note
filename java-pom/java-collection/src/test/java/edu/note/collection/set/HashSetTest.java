package edu.note.collection.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashSetTest {

    @Test
    @DisplayName("集合基本")
    void testBasic() {

        Set<String> set = new HashSet<>();

        // 添加成功返回 true
        assertTrue(set.add("aaa"));
        // 添加失败返回 false
        assertFalse(set.add("aaa"));
        set.add("bb");
        set.add("ccc");

        // 3.打印集合是无序的
        // System.out.println(s);
    }

    @Test
    @DisplayName("集合遍历")
    void testIterator() {
        /*
         * 利用Set系列的集合，添加字符串，并使用多种方式遍历。
         * 迭代器
         * 增强for
         * Lambda表达式
         */
        Set<String> set = new HashSet<>();
        Collections.addAll(set, "aaa", "bbb", "ccc");

        // 迭代器遍历
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }

        // 增强for
        for (String ele : set) {
            System.out.println(ele);
        }

        // Lambda表达式
        set.forEach(System.out::println);

    }

    @Test
    @DisplayName("多条件排序")
    void test01() {
        TreeSet<String> treeSet = new TreeSet<>((o1/*添加元素*/, o2/*已存在元素*/) -> {
            // 1.按长度排序
            int i = o1.length() - o2.length();
            // 2.长度相等, 按首字母排序
            i = i == 0 ? o1.compareTo(o2) : i;
            return i;
        });

        // 2.添加元素
        treeSet.add("c");
        treeSet.add("ab");
        treeSet.add("df");
        treeSet.add("foobar");

        System.out.println(treeSet);

    }

    @Test
    @DisplayName("不可变集合 Set.of，Java9")
    void test04() {
        // 一旦创建完毕之后，是无法进行修改的，在下面的代码中，只能进行查询操作
        // Set<String> set = Set.of("张三", "张三", "李四", "王五", "赵六");
    }
}
