package edu.note.algorithm.search;

import org.junit.jupiter.api.Test;

public class BinarySearchTest {

  public static int LowerBound(int[] arr, int target) {
    int low = 0;
    int high = arr.length - 1;
    int mid = 0;
    while (low < high) {
      mid = (high + low) / 2;
      if (arr[mid] > target)
        high = mid;
      else if (arr[mid] <= target)
        low = mid + 1;
    }
    return low;
  }

  @Test
  void test() {
    int[] a = { 2, 3, 3, 3, 4 };
    System.out.println(LowerBound(a, 3));
    // 输出字符串
    System.out.println("hello world");

    // 格式化输出浮点数 TODO:
    double pi = 3.1415926;
    System.out.printf("%.2f", pi);
  }

}
