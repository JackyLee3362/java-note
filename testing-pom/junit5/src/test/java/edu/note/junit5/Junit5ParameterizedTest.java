package edu.note.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author jackylee
 * @date 2025-12-09 19:42
 */
public class Junit5ParameterizedTest {

    @DisplayName("参数化测试")
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    void add(int a, int b, int exp) {
        assertEquals(exp, a + b);
    }

}
