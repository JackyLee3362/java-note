package edu.note.java.syntax;

import org.junit.jupiter.api.Test;

public class TestVolatile {

    volatile boolean initialized = false;

    @Test
    void test01() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }

    private void doInit() {

    }
}
