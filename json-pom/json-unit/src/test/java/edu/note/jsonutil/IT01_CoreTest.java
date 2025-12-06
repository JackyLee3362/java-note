package edu.note.jsonutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Configuration;
import net.javacrumbs.jsonunit.core.Option;

/**
 * @author jackylee
 * @date 2025-11-28 12:51
 */
public class IT01_CoreTest {
    @Test
    @DisplayName("测试 json 相等")
    void test01() {
        JsonAssert.assertJsonEquals("{\"a\":1, \"b\":2}", "{\"b\":2, \"a\":1}");
        JsonAssert.assertJsonNotEquals("{\"a\":[1,2,3]}", "{\"a\":[2,3,1]}");
        JsonAssert.assertJsonEquals("{\"a\":[1,2,3]}", "{\"a\":[2,3,1]}",
                Configuration.empty().when(Option.IGNORING_ARRAY_ORDER));
    }

    @Test
    @DisplayName("测试 json 相等")
    void test02() {
        // JsonAssert.assertJsonEquals("\"foo\":123,\"userId\":1", "{\"bar\":[2,3,3,1,4],\"userId\":1}",
        //         Configuration.empty().when(Option.IGNORING_EXTRA_FIELDS));
        JsonAssert.assertJsonEquals("\"userId\":1", "{\"userId\":1,\"bar\":[2,3,3,1,4]}",
                Configuration.empty().when(Option.IGNORING_EXTRA_FIELDS));
    }

}
