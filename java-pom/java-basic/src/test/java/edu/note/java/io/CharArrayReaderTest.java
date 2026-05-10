package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-05-10 12:19
 */
public class CharArrayReaderTest {

    @Test
    @DisplayName("测试")
    void test_01() throws IOException {
        String content = "你好";

        CharArrayReader reader = new CharArrayReader(content.toCharArray());
        assertEquals('你', reader.read());

        // 与 BytesArrayInputStream 的区别
        ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
        assertEquals(228, bis.read());
    }
}
