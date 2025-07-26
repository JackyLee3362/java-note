package edu.algorithm.cache;

import java.util.HashMap;

/**
 * @description: 自己实现 LRU
 * [TheAlgorithms/Java: All Algorithms implemented in Java](https://github.com/TheAlgorithms/Java)
 * @author: Jacky Lee
 * @date: 2024/3/16 23:43
 */
public class MyLRU<K, V> {
    int cap;
    HashMap<K, Node<K, V>> data = new HashMap<>();
    Node<K, V> head;
    Node<K, V> tail;

    public MyLRU(int capacity) {
        cap = capacity;
    }

    public void put(K key, V value) {
        // 如果 data 不包含 key，新增 Node
        Node<K, V> node = data.get(key);
        if (node == null) {
            addNewNode(new Node<>(key, value));
            return;
        }
        // 如果包含，则添加到末尾
        node.setValue(value);
        moveNodeToLast(node);
    }

    public V get(K key) {
        // 如果 data 不包含 key，直接返回 null
        Node<K, V> node = data.get(key);
        if (node == null) {
            return null;
        }
        // 如果包含，则添加到末尾
        moveNodeToLast(node);
        return node.getValue();
    }

    private Node<K, V> evict() {
        if (head == null) {
            throw new NullPointerException("Head为空，无法删除");
        }
        Node<K, V> node = head;
        node.setNext(null);
        node.setPrev(null);
        head = head.next;
        return node;
    }

    private void moveNodeToLast(Node<K, V> node) {
        // 如果本身已经是尾结点，则直接返回
        if (tail == node) {
            return;
        }
        // 取出前后两个结点
        Node<K, V> next = node.getNext();
        Node<K, V> prev = node.getPrev();
        if (next != null) {
            next.setPrev(prev);
        }
        if (prev != null) {
            prev.setNext(next);
        }
        tail.setNext(node);
        node.setPrev(tail);
        node.setNext(null);
        tail = node;
    }

    private void addNewNode(Node<K, V> node) {
        // 如果是新加的结点
        if (head == null) {
            data.put(node.getKey(), node);
            head = node;
            tail = node;
            return;
        }
        // 如果满了，则删除一个结点
        if (data.size() == cap) {
            Node<K,V> evictNode = evict();
            data.remove(evictNode.getKey());
        }
        moveNodeToLast(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V> point = head;
        while (point != null) {
            sb.append(point);
            point = point.getNext();
        }
        return sb.toString();
    }

    static final class Node<I, J> {
        private Node<I, J> next;
        private Node<I, J> prev;
        private I key;
        private J value;

        public Node(I key, J value) {
            this.key = key;
            this.value = value;
        }

        public Node(Node<I, J> next, Node<I, J> prev, I key, J value) {
            this.next = next;
            this.prev = prev;
            this.key = key;
            this.value = value;
        }


        public Node<I, J> getNext() {
            return next;
        }

        public void setNext(Node<I, J> next) {
            this.next = next;
        }

        public Node<I, J> getPrev() {
            return prev;
        }

        public void setPrev(Node<I, J> prev) {
            this.prev = prev;
        }

        public I getKey() {
            return key;
        }

        public void setKey(I key) {
            this.key = key;
        }

        public J getValue() {
            return value;
        }

        public void setValue(J value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + "=" + value + "]";
        }
    }

}
