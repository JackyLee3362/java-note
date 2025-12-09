package edu.note.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/6/30 19:42
 */
public class Junit5BeforeAfterAllTest {

    static String originData;

    @BeforeAll
    public static void beforeAll() {
        originData = "FOO";
    }

    @AfterAll
    public static void afterAll() {
        originData = null;
    }

    @Test
    void test01() {
        originData = "BAR";
        assertEquals("BAR", originData);
    }

    @Test
    void test02() {
        assertEquals("BAR", originData);
    }
}
