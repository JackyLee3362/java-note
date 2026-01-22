package edu.note.java.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-01-05 16:40
 */
public class StreamFilterTest {
    @Test
    @DisplayName("测试 filter")
    void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> list2 = list.stream().filter(n -> n >= 2).collect(Collectors.toList());
        assertEquals(2, list2.size());
    }
}
