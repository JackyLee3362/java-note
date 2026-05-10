package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/8/29 20:05
 */
public class ByteArrayInputStreamTest {

    @Test
    @DisplayName("字节输入流 - 构造函数1")
    void test01() throws IOException {
        // a=97, b=98, c=99, d=100
        byte[] bytes = new byte[] { 'a', 'b', 'c', 'd' };
        ByteArrayInputStream bis;

        // 构造函数 1
        bis = new ByteArrayInputStream(bytes);
        assertEquals('a', bis.read());
        bis.close();
    }

    @Test
    @DisplayName("字节输入流 - 构造函数2")
    void test02() throws IOException {
        // a=97, b=98, c=99, d=100
        byte[] bytes = new byte[] { 'a', 'b', 'c', 'd' };
        ByteArrayInputStream bis;

        // 构造函数 2
        bis = new ByteArrayInputStream(bytes, 1, 2);
        assertEquals('b', bis.read());
        assertEquals('c', bis.read());
        assertEquals(-1, bis.read());
        assertEquals(-1, bis.read());
        bis.close();

    }

}
