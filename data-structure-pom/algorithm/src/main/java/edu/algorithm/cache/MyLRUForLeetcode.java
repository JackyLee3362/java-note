package edu.algorithm.cache;

import java.util.HashMap;

/**
 * @description: 自己实现 LRU
 *               [TheAlgorithms/Java: All Algorithms implemented in
 *               Java](https://github.com/TheAlgorithms/Java)
 * @author: Jacky Lee
 * @date: 2024/3/16 23:43
 */
public class MyLRUForLeetcode {
    int cap;
    HashMap<Integer, Node<Integer, Integer>> data = new HashMap<>();
    Node<Integer, Integer> head;
    Node<Integer, Integer> tail;

    public MyLRUForLeetcode(int capacity) {
        cap = capacity;
    }

    public void put(int key, int value) {
        // 如果 data 不包含 key，新增 Node
        Node<Integer, Integer> node = data.get(key);
        if (node == null) {
            addNewNode(new Node<>(key, value));
            return;
        }
        // 如果包含，则添加到末尾
        node.setValue(value);
        moveNodeToLast(node);
    }

    public int get(int key) {
        // 如果 data 不包含 key，直接返回 null
        Node<Integer, Integer> node = data.get(key);
        if (node == null) {
            return -1;
        }
        // 如果包含，则添加到末尾
        moveNodeToLast(node);
        return node.getValue();
    }

    private Node<Integer, Integer> evict() {
        if (head == null) {
            throw new NullPointerException("Head为空，无法删除");
        }
        Node<Integer, Integer> node = head;
        node.setNext(null);
        node.setPrev(null);
        head = head.next;
        return node;
    }

    private void moveNodeToLast(Node<Integer, Integer> node) {
        // 如果本身已经是尾结点，则直接返回
        if (tail == node) {
            return;
        }
        // 取出前后两个结点
        Node<Integer, Integer> next = node.getNext();
        Node<Integer, Integer> prev = node.getPrev();
        if (next != null) {
            next.setPrev(prev);
        }
        if (prev != null) {
            prev.setNext(next);
        }
        if (head == node) {
            head = head.next;
        }
        tail.setNext(node);
        node.setPrev(tail);
        node.setNext(null);
        tail = node;
    }

    private void addNewNode(Node<Integer, Integer> node) {
        // 如果是新加的结点
        if (head == null) {
            data.put(node.getKey(), node);
            head = node;
            tail = node;
            return;
        }
        // 如果满了，则删除一个结点
        if (data.size() == cap) {
            Node<Integer, Integer> evictNode = evict();
            data.remove(evictNode.getKey());
        }
        moveNodeToLast(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Integer, Integer> point = head;
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

    public static void main(String[] args) {
        MyLRUForLeetcode lru = new MyLRUForLeetcode(2);
        lru.put(1, 10);
        System.out.println(lru);
        lru.put(2, 20);
        System.out.println(lru);
        System.out.println(lru.get(1));
        System.out.println(lru);
        lru.put(3, 30);
        System.out.println(lru.get(2));
        lru.put(4, 40);
        System.out.println(lru.get(1)); // -1
        System.out.println(lru.get(3)); // 30
        System.out.println(lru);
        System.out.println(lru.get(4)); // 40
        System.out.println(lru);
    }

}
