package edu.note.junit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author jackylee
 * @date 2025-12-09 19:58
 */
public class Junit4ExceptionTest {
    @Test
    public void test01() {
        try {
            int x = 1 / 0;
            fail("Should throw ArithmeticException");
        } catch (Exception e) {
            // ok
        }
    }
}
