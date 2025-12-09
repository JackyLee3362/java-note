package edu.note.junit4;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author jackylee
 * @date 2025-10-09 16:28
 */
public class Junit4BeforeAfterEachTest {
    @BeforeClass
    public static void beforeAll() {
        System.out.println("Before all tests");
    }

    @AfterClass
    public static void afterAll() {
        System.out.println("After all tests");
    }

    @Before
    public void beforeEach() {
        System.out.println("Before each test");
    }

    @After
    public void afterEach() {
        System.out.println("After each test");
    }

    @Test
    public void testHello() {
        assertEquals(2, 1 + 1);
    }
}
