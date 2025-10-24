package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * @author jackylee
 * @date 2025/8/29 20:05
 */
public class ByteStreamTest {

    @Test
    @DisplayName("字节输入流 - 构造函数")
    void test01() {
        // a=97, b=98, c=99, d=100
        byte[] bytes = new byte[]{'a', 'b', 'c', 'd'};
        ByteInputStream bis;

        // 构造函数 1
        bis = new ByteInputStream();
        assertEquals(-1, bis.read());

        // 构造函数 2
        bis = new ByteInputStream(bytes, 2);
        assertEquals('a', bis.read());
        assertEquals('b', bis.read());
        assertEquals(-1, bis.read());
        assertArrayEquals(bytes, bis.getBytes());

        // 构造函数 3
        bis = new ByteInputStream(bytes, 1, 2);
        assertEquals('b', bis.read());
        assertEquals('c', bis.read());
        assertEquals(-1, bis.read());

    }

    @Test
    @DisplayName("字节输出流 - 构造函数")
    void test02() {
        byte[] bytes = new byte[]{'a', 'b', 'c', 'd'};
        // 默认长度是 1024
        ByteOutputStream bout = new ByteOutputStream();
        bout.write(bytes);
        assertEquals(1024, bout.getBytes().length);
        bout.close();
    }

}
