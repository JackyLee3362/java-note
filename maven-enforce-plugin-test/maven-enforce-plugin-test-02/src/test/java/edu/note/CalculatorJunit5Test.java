package edu.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/14 17:09
 */
public class CalculatorJunit5Test {

    @Test
    void test() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(12, calculator.multiply(3, 4));
    }

}
