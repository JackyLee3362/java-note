package edu.note.algorithm.sort;

import org.junit.jupiter.api.Test;

public class FindKthLargestTest {
    @Test
    void testFindKthLargest() {
        int[] a1 = { 8, 4, 5, 6, 9, 3, 2, 7, 1, 0 };
        FindKthLargest fkl = new FindKthLargest();
        // System.out.println(fkl.findKthLargest(a1, 4) + "=>6");
        int[] a2 = { 3, 2, 1, 5, 6, 4 }; // 1,2,3,4,5,6
        System.out.println(fkl.findKthLargest(a2, 2) + "=>5");
        int[] a3 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        System.out.println(fkl.findKthLargest(a3, 4) + "=>4");
    }
}
