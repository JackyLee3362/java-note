package edu.note.junit4;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author jackylee
 * @date 2025-12-09 19:59
 */
@RunWith(Parameterized.class)
public class Junit4ParameterizedTest {

    @Parameterized.Parameters(name = "{index}: add({0}, {1}) = {2}")
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][] {
                { 1, 2, 3 },
                { 2, 3, 5 },
                { 0, 4, 4 }
        });
    }

    private int a, b, expected;

    public Junit4ParameterizedTest(int a, int b, int expected) {
        this.a = a;
        this.b = b;
        this.expected = expected;
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(expected, a + b);
    }
}
