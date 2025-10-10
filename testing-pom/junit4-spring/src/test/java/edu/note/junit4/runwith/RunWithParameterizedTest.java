package edu.note.junit4.runwith;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.note.junit4.Calculator;

/**
 * @author jackylee
 * @date 2025-10-09 16:30
 */
@RunWith(Parameterized.class)
public class RunWithParameterizedTest {
    private Integer numberA;
    private Integer numberB;
    private Integer expectedResult;

    public RunWithParameterizedTest(Integer numberA, Integer numberB, Integer expectedResult) {
        this.numberA = numberA;
        this.numberB = numberB;
        this.expectedResult = expectedResult;
    }

    // Method to provide test data
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1, 1, 2 }, // Test case 1
                { 2, 3, 5 }, // Test case 2
                { 5, 0, 5 } // Test case 3
        });
    }

    @Test
    public void test0() {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(numberA, numberB));
    }
}
