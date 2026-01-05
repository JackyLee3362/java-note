package edu.note.java.stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-11-26 10:54
 */
public class StreamMatchTest {
    @Test
    @DisplayName("allMatch 整体为 true 则返回 true, 空集为 true")
    void test01() {
        List<Integer> list = Collections.emptyList();
        boolean bool = list.stream().allMatch(i -> i == 0);
        assertTrue(bool);
    }

    @Test
    @DisplayName("anyMatch 任意为 true 则返回 true, 空集为 false")
    void test02() {
        List<Integer> list = Collections.emptyList();
        boolean bool = list.stream().anyMatch(i -> i == 0);
        assertFalse(bool);
    }

    @Test
    @DisplayName("noneMatch 整体为 false 则返回 false, 空集为 true")
    void test03() {
        List<Integer> list = Collections.emptyList();
        boolean bool = list.stream().noneMatch(i -> i == 0);
        assertTrue(bool);
    }

}
