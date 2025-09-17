package 其他.d38_期末考核.a06_第六题;

import java.util.Comparator;

public class MyLinkedList<E> {
    // 封装一个链表
    Node<E> head = null;


    /**
     * 定义了一个私有的内部类，作为链表的结点。
     */
    public static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public Node<E> add(E e) {
        if (head == null) {
            head = new Node(e, null);
        } else {
            // 往后面插入结点。（尾插法）
            Node<E> temp = head;
            // 让temp走到尾部结点
            while (temp.next != null) {
                temp = temp.next;
            }
            // 把当前结点创建出来，加入到尾部结点
            temp.next = new Node(e, null);
        }
        return head;
    }

    public void sort(Comparator<E> comparator) {
        head = insertionSort(head, comparator);
    }

    private Node<E> insertionSort(Node<E> head, Comparator<E> comparator) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<E> sorted = null;
        Node<E> current = head;

        while (current != null) {
            Node<E> next = current.next;
            sorted = insert(sorted, current, comparator);
            current = next;
        }

        return sorted;
    }

    private Node<E> insert(Node<E> sorted, Node<E> newNode, Comparator<E> comparator) {
        if (sorted == null || comparator.compare(sorted.data, newNode.data) >= 0) {
            newNode.next = sorted;
            return newNode;
        }

        Node<E> current = sorted;
        while (current.next != null && comparator.compare(current.next.data, newNode.data) < 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

        return sorted;
    }

    // ...（其他代码）

    public MyLinkedList<E> merge(MyLinkedList<E> list2, Comparator<E> comparator) {
        MyLinkedList<E> mergedList = new MyLinkedList<>();

        Node<E> current1 = this.head;
        Node<E> current2 = list2.head;

        while (current1 != null && current2 != null) {
            if (comparator.compare(current1.data, current2.data) <= 0) {
                mergedList.add(current1.data);
                current1 = current1.next;
            } else {
                mergedList.add(current2.data);
                current2 = current2.next;
            }
        }

        // 处理剩余的元素
        while (current1 != null) {
            mergedList.add(current1.data);
            current1 = current1.next;
        }

        while (current2 != null) {
            mergedList.add(current2.data);
            current2 = current2.next;
        }

        return mergedList;
    }
}
