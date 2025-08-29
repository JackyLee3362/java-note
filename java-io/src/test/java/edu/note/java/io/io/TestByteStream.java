package edu.note.java.io.io;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/8/29 20:05
 */
public class TestByteStream {

    @Test
    @Disabled("字节输入流 - 构造函数")
    void test_01() {
        // a=97, b=98, c=99, d=100
        byte[] bytes = new byte[]{'a', 'b', 'c', 'd'};
        ByteInputStream bis;

        // 构造函数 1
        bis = new ByteInputStream();
        Assertions.assertEquals(-1, bis.read());

        // 构造函数 2
        bis = new ByteInputStream(bytes, 2);
        Assertions.assertEquals('a', bis.read());
        Assertions.assertEquals('b', bis.read());
        Assertions.assertEquals(-1, bis.read());
        Assertions.assertArrayEquals(bytes, bis.getBytes());

        // 构造函数 3
        bis = new ByteInputStream(bytes, 1, 2);
        Assertions.assertEquals('b', bis.read());
        Assertions.assertEquals('c', bis.read());
        Assertions.assertEquals(-1, bis.read());

    }

    @Test
    void test_02() {
        byte[] bytes = new byte[]{'a', 'b', 'c', 'd'};
        // 默认长度是 1024
        ByteOutputStream bout = new ByteOutputStream();
        bout.write(bytes);
        Assertions.assertEquals(1024, bout.getBytes().length);
        bout.close();

    }

}
