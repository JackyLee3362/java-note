package edu.note.java.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamCollectorsTest {

    public static final List<String> list = Arrays.asList("a", "b", "c", "a");

    @Test
    @DisplayName("转换为list")
    void testToList() {
        List<String> res = list.stream().filter(s -> s.equals("a")).collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "a"), res);
    }

    @Test
    @DisplayName("转换为Set")
    void testToSet() {
        Set<String> res = list.stream().filter(s -> s.equals("a")).collect(Collectors.toSet());
        Set<String> expected = new HashSet<>();
        expected.add("a");
        assertEquals(expected, res);
    }

    @Test
    @DisplayName("转换为map")
    void testMapping() {
        List<String> list = Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B", "C"), list);
    }

    @Test
    @DisplayName("Collectors.groupingBy")
    void testGroupingBy() {
        Map<Integer, List<String>> map = Stream.of("alice", "bob", "cindy").collect(Collectors.groupingBy(
                String::length));
        Stream.of(1, 2, 3).collect(Collectors.toList());
        assertEquals(2, map.get(5).size());
    }

    @Test
    @DisplayName("测试 Optional")
    void testOptional() {
        List<String> list = Arrays.asList(null, "Foo");
        String s = list.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        assertEquals("Foo", s);
        List<String> list2 = new ArrayList<>();
        list2.add(null);
        list2.add(null);
        String s2 = list2.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        assertEquals(null, s2);
    }

}
