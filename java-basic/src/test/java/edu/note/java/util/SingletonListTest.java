package edu.note.java.util;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/8/22 16:24
 */
public class SingletonListTest {

    @Test
    void test_singleton_list() {
        List<String> list = Collections.singletonList("a");
        // 只能有一个元素
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals("a", list.get(0));
        // 无法删除
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
        // 无法修改
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.set(0, "b"));
        // 无法新增
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.add("b"));
    }

}
