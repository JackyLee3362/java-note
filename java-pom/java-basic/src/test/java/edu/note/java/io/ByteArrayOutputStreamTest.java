package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-05-10 14:59
 */
public class ByteArrayOutputStreamTest {
    @Test
    @DisplayName("字节输出流 - 构造函数")
    void test02() throws IOException {
        byte[] bytes = new byte[] { 'a', 'b', 'c', 'd' };
        // 默认长度是 1024
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(bytes);
        assertArrayEquals(bytes, bos.toByteArray());
        bos.close();
    }
}
