package edu.note.java.function;

import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class PredicateTest {
    @Test
    void test1() {
        Predicate<String> isLongerThan3 = str -> str.length() > 3;
        Predicate<String> isShortThan10 = str -> str.length() < 10;
        Predicate<String> lengthBetween3and10 = isLongerThan3.or(isShortThan10);
        System.out.println(lengthBetween3and10.test("123"));
    }
}
