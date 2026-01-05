package edu.note.java.collection.source;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class SourceTreeMap {
    // 这段代码演示了为什么TreeMap中构造方法的Comparator<? super K>
    // 一开始我以为是 应该是 Comparator<? extend K>，实际错了
    // 假设K是Fa，然后Fa继承Ye，Ye实现了Comparable，则实际是调用了Ye的compareTo
    @Test
    @DisplayName("")
    void test01() {
        TreeMap<Fa, String> tree = new TreeMap<>();
        Fa f1 = new Fa();
        tree.put(f1, "111");

    }

}

class Ye implements Comparable<Ye> {
    int weight;

    @Override
    public int compareTo(Ye o) {
        return this.weight - o.weight;
    }
}

class Fa extends Ye {
    int age;

}

class Son extends Fa {

}