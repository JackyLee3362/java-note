package edu.note.collection.map;

public class HashMapSourceNote<K, V> {
    // HashMap源码笔记
    transient Node<K, V>[] table;

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    ;

    static final class TreeNode<K, V> extends Node<K, V> {

        TreeNode(int hash, K key, V value, Node<K, V> next) {
            super(hash, key, value, next);
        }
    }

    final Node<K, V>[] resize() {
        return null;
    }

    ;

    V putVal(int hash, K key, V value, boolean onlyIfAbsent,
            boolean evict) {

        // 1. 定义局部变量
        Node<K, V>[] tab; // 用来记录table指针的局部变量
        Node<K, V> p; // table中具体的Node指针
        int n, i;

        // 2. 如果tab是null，或者长度为0，则调用resize()方法
        // resize：初始化或者使原数组增长为原来的2倍
        tab = table;
        n = tab.length;
        if (tab == null || n == 0) {
            tab = resize(); // 这里是初始化table数组
            n = tab.length;

        }
        i = (n - 1) & hash;
        p = tab[i];

        // 3. 如果当前table数组的位置没有任何结点（null），则创建结点
        if (p == null) {
            // 创建新结点
            // tab[i] = newNode(hash, key, value, null);
        }
        // 4. 如果存在结点，那么继续判断
        else {
            Node<K, V> e = null;
            K k;
            if (p.hash == hash && // 如果和p的hash值相等 且 键值p.key和key值相等，则让e等于p
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode) // 如果p是树结点
            {
                // 那么将p强转为树结点类型，调用putTreeVal方法
                // e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);

            } else { //
                // 这里其实就是一个while循环
                for (int binCount = 0;; ++binCount) {
                    if ((e = p.next) == null) { // 如果e的next结点为空，
                        // p.next = newNode(hash, key, value, null);

                        // 如果超过阈值，则将链表转为红黑树结点
                        int TREEIFY_THRESHOLD = 8;
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        {
                            // treeifyBin(tab, hash);

                        }
                        break;
                    }
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                // afterNodeAccess(e);
                return oldValue;
            }
        }
        // ++modCount; // 增加操作数
        // if (++size > threshold)
        // resize();
        // afterNodeInsertion(evict);
        return null;
    }
}
