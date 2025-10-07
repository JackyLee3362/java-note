package edu.note.algorithm.cache;

import org.junit.jupiter.api.Test;

import edu.note.algorithm.cache.MyLRU;

class MyLRUTest {
    @Test
    void testMyLRU() {
        MyLRU<Integer, String> lru = new MyLRU<Integer, String>(3);
        lru.put(1, "first");
        System.out.println(lru);
        lru.put(2, "second");
        System.out.println(lru);
        lru.put(3, "third");
        System.out.println(lru);
        System.out.println(lru.get(1));
        System.out.println(lru);
    }

}