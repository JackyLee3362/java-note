package edu.note;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jackylee
 * @date 2025/9/14 21:20
 */
public class CalculatorJunit4Test {

    @Test
    public void test() {
        Calculator calculator = new Calculator();
        String res = "Hello";
        Assert.assertEquals("Hello", res);
    }

}
