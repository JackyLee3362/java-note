package edu.note.collection.collection;

import java.util.ArrayList;
import java.util.Collection;

public class A06_增强for遍历 {
    public static void main(String[] args) {
       /* Collection系列集合三种通用的遍历方式：
        1.迭代器遍历
        2.增强for遍历
        3.lambda表达式遍历

        增强for格式：
            for(数据类型 变量名: 集合/数组){

            }
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
}
