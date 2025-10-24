package edu.note.collection.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/8/22 16:24
 */
public class SingletonListTest {

    @Test
    @DisplayName("测试单例列表")
    void test_singleton_list() {
        List<String> list = Collections.singletonList("a");
        // 只能有一个元素
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
        // 无法删除
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
        // 无法修改
        assertThrows(UnsupportedOperationException.class, () -> list.set(0, "b"));
        // 无法新增
        assertThrows(UnsupportedOperationException.class, () -> list.add("b"));
    }

}
