package edu.note.java.stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StreamCollectorsTest {

    public static final List<String> list = Arrays.asList("a", "b", "c", "a");


    @Test
    @DisplayName("转换为list")
    void testToList() {
        List<String> res = list.stream().filter(s -> s.equals("a")).collect(Collectors.toList());
        Assertions.assertEquals(Arrays.asList("a", "a"), res);
    }

    @Test
    @DisplayName("转换为Set")
    void testToSet() {
        Set<String> res = list.stream().filter(s -> s.equals("a")).collect(Collectors.toSet());
        Set<String> expected = new HashSet<>();
        expected.add("a");
        Assertions.assertEquals(expected, res);
    }

    @Test
    @DisplayName("转换为map")
    void testMapping() {
        List<String> list = Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.toList());
        Assertions.assertEquals(Arrays.asList("A", "B", "C"), list);
        List<String> list2 = Stream.of("a", "b", "c").map(String::toUpperCase).collect(Collectors.toList());
    }

    @Test
    @DisplayName("Collectors.groupingBy")
    void testGroupingBy() {
        Map<Integer, List<String>> map = Stream.of("alice", "bob", "cindy").collect(Collectors.groupingBy(
            String::length));
        Assertions.assertEquals(2, map.get(5).size());
    }

}
