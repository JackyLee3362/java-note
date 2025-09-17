package edu.note.java.advance.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCollectors {
    @Test
    @DisplayName("Collectors.toList()")
    void testToList() {
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        Assertions.assertEquals(Arrays.asList("a", "b", "c"), list);
    }

    @Test
    @DisplayName("Collectors.mapping")
    void testMapping() {
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.mapping(
                s -> s.toUpperCase(),
                Collectors.toList()));
        Assertions.assertEquals(Arrays.asList("A", "B", "C"), list);
    }

    @Test
    @DisplayName("Collectors.groupingBy")
    void testGroupingBy() {
        Map<Integer, List<String>> map = Stream.of("alice", "bob", "cindy").collect(Collectors.groupingBy(
                String::length));
        Assertions.assertEquals(2, map.get(5).size());
    }

}
