package edu.note.junit.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jackylee
 * @date 2025/6/30 19:42
 */
public class TestCalculator {
    // @AfterAll
    // @AfterEach
    // 同理
    // Disable 被注解后不参与测试
    // DisplayName 测试名称

    @BeforeAll
    public static void beforeAllTest() {
        System.out.println("所有测试前准备...");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("单个测试前准备...");
    }

    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
        "0,    1,   1",
        "1,    2,   3",
        "49,  51, 100",
        "1,  100, 101"
    })
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
            () -> first + " + " + second + " should equal " + expectedResult);
    }

    @Test
    @Disabled
    void disable(){

    }

}
