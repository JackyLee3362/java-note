package com.example.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMain {
    @Test
    public static void main(String[] args) {
        System.out.println("hello, test");
        TestLog.printLog();
        TestAssert.printAssert();
    }
}
