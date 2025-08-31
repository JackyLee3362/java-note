package edu.note.collection.set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TestSet {
    @Test
    @DisplayName("集合迭代")
    void testIterator() {
        /*
         * 利用Set系列的集合，添加字符串，并使用多种方式遍历。
         * 迭代器
         * 增强for
         * Lambda表达式
         */

        // 1.创建一个Set集合的对象
        Set<String> set = new HashSet<>();

        // 2,添加元素
        // 如果当前元素是第一次添加，那么可以添加成功，返回true
        // 如果当前元素是第二次添加，那么添加失败，返回false
        set.add("张三");
        set.add("张三");
        set.add("李四");
        set.add("王五");

        // 3.打印集合
        // 无序
        // System.out.println(s);//[李四, 张三, 王五]

        // 迭代器遍历
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }

        // 增强for
        for (String ele : set) {
            // ...
        }

        // Lambda表达式
        set.forEach(ele -> System.out.println(ele));

    }

    @Test
    @DisplayName("排序")
    void test01() {
        /*
         * 需求：请自行选择比较器排序和自然排序两种方式；
         * 要求：存入四个字符串， “c”, “ab”, “df”, “qwer”
         * 按照长度排序，如果一样长则按照首字母排序
         * 
         * 采取第二种排序方式：比较器排序
         */

        // 1.创建集合
        // o1:表示当前要添加的元素
        // o2：表示已经在红黑树存在的元素
        // 返回值规则跟之前是一样的
        TreeSet<String> ts = new TreeSet<>((o1, o2) -> {
            // 按照长度排序
            int i = o1.length() - o2.length();
            // 如果一样长则按照首字母排序
            i = i == 0 ? o1.compareTo(o2) : i;
            return i;
        });

        // 2.添加元素
        ts.add("c");
        ts.add("ab");
        ts.add("df");
        ts.add("qwer");

    }
}
