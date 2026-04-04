package edu.note.java.stream;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-03-26 10:21
 */
public class StreamSplitTest {

    @Test
    @DisplayName("测试 Split")
    void test01() {
        List<String> list = Arrays.asList("a", "b", "c");
        list.spliterator().forEachRemaining(item -> {
            System.out.println(item);
        });
    }

}
