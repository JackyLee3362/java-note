package edu.note.jsonutil;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.javacrumbs.jsonunit.core.Option;

/**
 * @author jackylee
 * @date 2025-11-27 17:04
 */
public class IT02_JsonUnitAssertJTest {

    @Test
    @DisplayName("测试 Assert Json")
    void test01() {
        assertThatJson("{\"a\":1, \"b\":2}").isEqualTo("{b:2, a:1}");
        assertThatJson("{\"a\":[1,2,3]}").when(Option.IGNORING_ARRAY_ORDER).isEqualTo("{\"a\":[2,3,1]}");
        assertThatJson("{\"a\":[1,2,3]}").isEqualTo("{\"a\":[2,3,1]}");
    }

}
