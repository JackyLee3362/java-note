package edu.note.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Junit5BeforeAfterEachTest {

    String originalData;

    @BeforeEach
    void before() throws IOException {
        originalData = "FOO";
    }

    @AfterEach
    void after() throws IOException {
        originalData = null;
    }

    @Test
    void test01() {
        assertEquals("FOO", originalData);
    }

}
