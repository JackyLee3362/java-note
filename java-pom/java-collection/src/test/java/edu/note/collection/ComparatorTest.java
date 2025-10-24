package edu.note.collection;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/26 09:01
 */
public class ComparatorTest {

    @Test
    @DisplayName("compare 方法")
    void test01() {
        // Comparator 中的 compare(o1,o2) 返回
        // 1. 负数: 说明o1该排在前面
        // 2. 0  : 说明顺序应该保持不变
        // 3. 正数: 说明o2该在前面
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
        assertEquals(2, comparator.compare(1, 2));
    }

    @Test
    @DisplayName("Comparator 应用")
    void test02() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        assertEquals("[1, 3, 2]", list.toString());
        list.sort((o1, o2) -> o2 - o1);
        assertEquals("[3, 2, 1]", list.toString());
    }

}
