package edu.note;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/14 17:09
 */
public class CalculatorJunit5Test {

    @Test
    void test() {
        Calculator calculator = new Calculator();
        assertEquals(12, calculator.multiply(3, 4));
    }

}
