package edu.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-12-09 13:15
 */
public class CollectionUtilTest {

    @Test
    @DisplayName("测试")
    void test01() {

        assertTrue(CollectionUtils.isEmpty(null));
        assertTrue(CollectionUtils.isEmpty(new ArrayList<>()));
        
        List<String> list = new ArrayList<>();
        list.add(null);

        assertFalse(CollectionUtils.isEmpty(list));
        assertFalse(CollectionUtils.isEmpty(Arrays.asList(null, null)));
        assertFalse(CollectionUtils.isEmpty(Arrays.asList(null, "FOO")));
    }

}
