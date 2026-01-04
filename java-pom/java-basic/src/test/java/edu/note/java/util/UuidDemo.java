package edu.note.java.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-19 13:48
 */
public class UuidDemo {

    @Test
    @DisplayName("测试")
    void test01() {
        // when:
        String str = UUID.randomUUID().toString().replace("-", "");
        // 类似 9f15b8c356c54f55bfcb0ee3023fce8a

        // then:
        assertNotNull(str);
        assertEquals(32, str.length());

    }

}
