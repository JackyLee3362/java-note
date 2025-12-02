package edu.note.java.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-12-02 14:38
 */
public class StringJoinerTest {

    @Test
    @DisplayName("测试 StringJoiner 基础")
    void test01() {
        // given:
        StringJoiner sj = new StringJoiner("\n");
        sj.add("Foo");
        sj.add("Bar");

        // expect:
        assertEquals("Foo\nBar", sj.toString());
    }

    @Test
    @DisplayName("测试 StringJoiner 和 List<String>")
    void test12() {
        // given:
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("Foo");
        sj.add("Bar");
        List<String> list = Arrays.asList("Foo", "Bar");


        // expect:
        assertEquals("[Foo,Bar]", sj.toString());
        assertEquals("Foo,Bar", String.join(",", list));
        assertEquals("[Foo, Bar]", list.toString());
    }

    @Test
    @DisplayName("测试 StringJoiner")
    void test13() {
        // given:
        StringJoiner sj = new StringJoiner(",", "[", "");
        sj.add("Foo");
        sj.add("Bar");

        // expect:
        assertEquals("[Foo,Bar", sj.toString());
    }

}
