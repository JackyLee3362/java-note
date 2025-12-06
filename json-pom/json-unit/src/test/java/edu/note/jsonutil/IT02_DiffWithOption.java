package edu.note.jsonutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;
import net.javacrumbs.jsonunit.core.internal.Diff;

/**
 * @author jackylee
 * @date 2025-12-05 14:25
 */
public class IT02_DiffWithOption {

    @Test
    @DisplayName("测试 Diff 列表，忽略列表顺序")
    void testApiDiffArray() {
        // when
        Configuration config = Configuration.empty().when(Option.IGNORING_ARRAY_ORDER);
        Diff diff = Diff.create("[4,1,2,3,3]", "{\"foo\":[2,3,3,1,4]}", "", "foo", config);
        Diff diff2 = Diff.create("[4,1,2,3,3]", "[2,3,3,1,4]", "", "", config);
        Diff diff3 = Diff.create("[4,1,null,3,3]", "[null,3,3,1,4]", "", "", config);
        Diff diff4 = Diff.create("[4,1,null,1,null]", "[null,1,null,1,4]", "", "", config);

        // then
        assertTrue(diff.similar());
        assertTrue(diff2.similar());
        assertTrue(diff3.similar());
        assertTrue(diff4.similar());
    }

    @Test
    @DisplayName("测试 Diff 列表，忽略 actual 的额外字段")
    void testApiDiffExistField() {
        // when
        Configuration config = Configuration.empty().when(Option.IGNORING_EXTRA_FIELDS);
        Diff diff = Diff.create("\"foo\":123,\"userId\":1", "{\"bar\":[2,3,3,1,4],\"userId\":1}", "", "", config);
        Diff diff2 = Diff.create("\"foo\":123,\"userId\":1", "{\"userId\":1}", "", "", config);

        // then
        assertFalse(diff.similar());
        assertFalse(diff2.similar());
    }

    @Test
    @DisplayName("测试 Diff 列表,忽略 actual 的多余元素")
    void testApiDiffListSet() {
        // when
        Configuration config = Configuration.empty().when(Option.IGNORING_EXTRA_ARRAY_ITEMS);
        Diff diff = Diff.create("[2,3,1,4]", "[2,3,1,4,1,2,3]", "", "", config);

        // then
        assertTrue(diff.similar());
    }


}
