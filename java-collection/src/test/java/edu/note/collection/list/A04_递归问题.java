package edu.note.collection.list;

import java.util.ArrayList;
import java.util.List;

public class A04_递归问题 {
    public static void main(String[] args) {
        List<Object> myList = new ArrayList<>();
        myList.add("Item 1");
        myList.add(myList);  // 自引用，将集合本身添加到集合中
        System.out.println(myList); // [Item 1, (this Collection)]
        // 这里可以看AbstractCollection中的toString源码，因为防止无限递归，所以会出现(this Collection)
    }
}
