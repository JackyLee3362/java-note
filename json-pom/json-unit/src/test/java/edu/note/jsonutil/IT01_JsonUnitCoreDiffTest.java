package edu.note.jsonutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;
import net.javacrumbs.jsonunit.core.internal.Diff;

/**
 * Diff.create(
 * expected: 期望Json,
 * actual: 实际Json,
 * actualName: 输出报告名(比如 json diff, response 等),
 * path: 为空字符串时表示比较整个 json, 否则按照路径比较，"a" 只对比 a 节点
 * configuration: 比较规则配置，比如数组顺序、容差、字段忽略等
 * 
 * @author jackylee
 * @date 2025-11-28 14:47
 * @see https://github.com/lukas-krecan/JsonUnit/blob/655e035a7639ef474e420619fe48fdfa8f406279/json-unit-core/src/test/java/net/javacrumbs/jsonunit/core/internal/DifferenceTest.java
 */
public class IT01_JsonUnitCoreDiffTest {

    static final Configuration config = Configuration.empty();

    @Test
    @DisplayName("测试 json Diff 空")
    void testApiDiffEmptyV2() {
        // when
        Diff diff = Diff.create("{\"a\":2,\"b\":3}", "{\"a\":1,\"b\":2}", "", "", config);

        // then
        // assertTrue(diff.similar());
        System.out.println(diff.differences());
    }

    @Test
    @DisplayName("测试 json Diff 空")
    void testApiDiffEmpty() {
        // when
        Diff diff = Diff.create("{}", "{}", "", "", config);

        // then
        assertTrue(diff.similar());
    }

    @Test
    @DisplayName("测试字符串")
    void testApiDiffString() {
        // when
        Diff diff = Diff.create("foo", "\"foo\"", "", "", config);
        Diff diff2 = Diff.create("\"foo\"", "\"foo\"", "", "", config);

        // then
        assertTrue(diff.similar());
        assertTrue(diff2.similar());
    }

    @Test
    @DisplayName("测试 json Diff 路径")
    void testApiDiff() {
        // when
        Diff diff = Diff.create("[1,2,3]", "{\"foo\":[1,2,3]}", "", "foo", config);

        // then
        assertTrue(diff.similar());
    }

    @Test
    @DisplayName("测试 json Diff 列表")
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
    @DisplayName("测试 json Diff Null")
    void testApiDiffNull() {
        // when
        Diff d1 = Diff.create(null, null, "", "", config); // null == null
        Diff d2 = Diff.create("", null, "", "", config); // "" != null
        Diff d3 = Diff.create(null, "", "", "", config); // null != ""

        Diff d4 = Diff.create(null, null, "", "a", config); // null != <missing>
        Diff d5 = Diff.create("", null, "", "a", config); // "" != <missing>
        Diff d6 = Diff.create(null, "", "", "a", config); // null != <missing>
        Diff d7 = Diff.create(null, "{\"a\":null}", "", "a", config); // null == null
        // then
        assertTrue(d1.similar());
        assertFalse(d2.similar());
        assertFalse(d3.similar());
        assertFalse(d4.similar()); // 重点关注
        assertFalse(d5.similar());
        assertFalse(d6.similar());
        assertTrue(d7.similar());
    }

}
