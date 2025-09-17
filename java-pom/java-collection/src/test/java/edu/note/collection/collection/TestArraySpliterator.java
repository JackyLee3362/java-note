package edu.note.collection.collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Spliterator;
import java.util.Spliterators;
import org.junit.jupiter.api.Test;

public class TestArraySpliterator {

    @Test
    void test() {
        String[] names = { "Alice", "Bob", "Charlie", "Dave", "Eve" };

        // 创建一个 ArraySpliterator
        Spliterator<String> spliterator = Spliterators.spliterator(names,
                Spliterator.ORDERED | Spliterator.IMMUTABLE);

        // 遍历并输出每个元素
        spliterator.forEachRemaining(System.out::println);
    }
}
