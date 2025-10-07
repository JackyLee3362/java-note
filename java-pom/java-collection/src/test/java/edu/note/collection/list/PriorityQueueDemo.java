package edu.note.collection.list;

import java.util.PriorityQueue;

class Point implements Comparable<Point> {
  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getXY() {
    return x + y;
  }

  @Override
  public int compareTo(Point p) {
    return getXY() - p.getXY();
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}

public class PriorityQueueDemo {
  public static void test1() {
    // 自定义优先队列
    PriorityQueue<Point> points = new PriorityQueue<>();
    // 添加元素
    points.add(new Point(3, 2));
    points.add(new Point(2, 4));
    points.add(new Point(0, 1));
    // 查看第一个元素
    System.out.println(points.peek());
    // 依次弹出元素
    while (true) {
      Point p = points.poll();
      System.out.println(p);
      if (p == null)
        break;
    }
  }

  public static void test2() {
    // 小根堆
    PriorityQueue<Integer> heap_small = new PriorityQueue<>((a, b) -> a - b);
    heap_small.add(new Integer(1));
    heap_small.add(new Integer(2));
    System.out.println(heap_small.peek());
    // 大根堆
    PriorityQueue<Integer> heap_big = new PriorityQueue<>((a, b) -> b - a);
    heap_big.add(new Integer(1));
    heap_big.add(new Integer(2));
    System.out.println(heap_big.peek());
  }

  public static void test3() {
    // 对数组来说
    PriorityQueue<int[]> heap_array = new PriorityQueue<>((int[] a, int[] b) -> a[0] - b[0]);
    heap_array.add(new int[2]);
    System.out.println(heap_array.peek());

  }

  public static void main(String[] args) {
    test3();
  }
}
