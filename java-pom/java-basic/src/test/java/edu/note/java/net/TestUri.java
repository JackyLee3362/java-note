package edu.note.java.net;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-15 19:23
 */
public class TestUri {

    @Test
    @DisplayName("测试")
    void test_basic_01() {
        // given:
        URI uri = URI.create("http://www.baidu.com:8080/path/to/resource?query=123#fragment");
        // expect:
        assertEquals("http", uri.getScheme());
        assertEquals("www.baidu.com", uri.getHost());
        assertEquals(8080, uri.getPort());
        assertEquals("/path/to/resource", uri.getPath());
        assertEquals("query=123", uri.getQuery());
        assertEquals("fragment", uri.getFragment());
    }

    @Test
    @DisplayName("测试 URL")
    void test_basic_02() {
        // given:
        String url = "/path/to/resource";
        int index = "/path/to/resource".indexOf("/", 2);
        String substring = url.substring(index + 1);
        System.out.println(substring);

        // when:
        // then:
    }

}
