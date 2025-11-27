package edu.note.jsonutil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;

/**
 * @author jackylee
 * @date 2025-11-27 17:04
 */
public class IT01_DemoTest {
    @Test
    @DisplayName("测试 Assert Json")
    void test01() {
        assertThatJson("{\"a\":1, \"b\":2}").isEqualTo("{b:2, a:1}");
    }

}
