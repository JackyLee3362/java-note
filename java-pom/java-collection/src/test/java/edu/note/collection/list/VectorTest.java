package edu.note.collection.list;

import java.util.Stack;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class VectorTest {
    /*
     * java中的stack本质上是vector
     */
    @Test
    void testStack() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        int temp = stack.peek();
        stack.pop();
        System.out.println(temp);
    }

    @Test
    void testVector() {
        Vector<Integer> vector = new Vector<Integer>();
        vector.size();
        vector.add(1);
        vector.add(2);
        vector.get(1);
        vector.elementAt(0);
        vector.capacity();

    }

}
