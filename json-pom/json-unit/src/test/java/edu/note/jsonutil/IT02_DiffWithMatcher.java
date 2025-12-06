package edu.note.jsonutil;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.internal.Diff;

/**
 * @author jackylee
 * @date 2025-12-05 14:32
 */
public class IT02_DiffWithMatcher {

    @Test
    @DisplayName("测试自定义规则")
    void testCustomRule() {
        // when
        
        Configuration config = Configuration.empty().withMatcher("忽略 expected 或 actual 不存在的字段", null);

        Diff diff = Diff.create("\"foo\":123,\"userId\":1", "{\"bar\":[2,3,3,1,4],\"userId\":1}", "", "", config);
        Diff diff2 = Diff.create("\"foo\":123,\"userId\":1", "{\"userId\":1}", "", "", config);

        // then
        assertFalse(diff.similar());
        assertFalse(diff2.similar());
    }

    @Test
    @DisplayName("测试自定义规则")
    void testWithIgnorePlaceholder() {
        // when: ignorePlaceholder 用于忽略 actual 中的特定占位符值，方便自动化测试忽略敏感字段、动态字段、随机值等
        Configuration config = Configuration.empty().withIgnorePlaceholder(null);
        Diff diff = Diff.create("\"foo\":123,\"userId\":1", "{\"bar\":[2,3,3,1,4],\"userId\":1}", "", "", config);
        Diff diff2 = Diff.create("\"foo\":123,\"userId\":1", "{\"userId\":1}", "", "", config);

        // then
        assertFalse(diff.similar());
        assertFalse(diff2.similar());
    }
}
