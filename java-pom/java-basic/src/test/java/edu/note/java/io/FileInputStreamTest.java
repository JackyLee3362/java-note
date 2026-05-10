package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/29 下午3:51
 */
public class FileInputStreamTest  {

    @Test
    @DisplayName("循环读取")
    void test01() throws IOException {
        File read = IOUtil.createTempFile("hello");
        FileInputStream inputStream = new FileInputStream(read);
        // 2.循环读取
        int b;
        while ((b = inputStream.read()) != -1) {
            System.out.print((char) b);
        }
        // 3.释放资源
        inputStream.close();

    }

    @Test
    @DisplayName("Buffer 读取")
    void test02() throws IOException {
        File read = IOUtil.createTempFile("hello");
        FileInputStream inputStream = new FileInputStream(read);

        // 2.读取数据，每次4字节
        byte[] bytes = new byte[4];

        int len;
        String str;
        while ((len = inputStream.read(bytes)) != -1) {
            System.out.println("读取长度为 " + len);// 3
            str = new String(bytes, 0, len);
            System.out.println(str);
        }
        // 3.释放资源
        inputStream.close();
    }

    @Test
    @DisplayName("Question: Emoji/中文 如何读取")
    void test07() throws IOException {
        File emojiFile = IOUtil.createTempFile("😄");
        // code point>=65536 怎么办？
        // 因为是字节流读取，所以无法正确显示
        FileInputStream f = new FileInputStream(emojiFile);
        // 2.循环读取
        int b;
        while ((b = f.read()) != -1) {
            System.out.print(Integer.toBinaryString(b) + " ");
            // System.out.println((char) b);
        }
        // 3.释放资源
        f.close();

    }

    @Test
    @DisplayName("写入文件")
    void test_write_01() throws IOException {
        File read = IOUtil.createTempFile("hello");
        File write = IOUtil.createWritableFile("write.txt");
        FileOutputStream fos = new FileOutputStream(write);
        // 先写一个 'a'
        fos.write(97);
        // 再写一个换行 '\r\n'
        fos.write("\r\n".getBytes());
        // 再写一个 'bc'
        byte[] bytes = { 97, 98, 99, 100, 101 };
        fos.write(bytes, 1, 2);
        fos.close();
        assertEquals(5, read.length());
    }

    @Test
    @DisplayName("未释放资源无法删除文件")
    void test_write_02() throws IOException {
        File read = IOUtil.createTempFile("hello");
        FileOutputStream fos = new FileOutputStream(read);
        fos.write(97);
        // assertFalse(read.delete()); // 这里主要取决于操作系统，windows 返回 false，macos 返回 true
        fos.close();
        assertTrue(read.delete());
    }

    @Test
    @DisplayName("拷贝文件")
    void test_copy_01() throws IOException {

        File read = IOUtil.createTempFile("hello");
        File write = IOUtil.createWritableFile("write.txt");
        FileInputStream fis = new FileInputStream(read);
        FileOutputStream fos = new FileOutputStream(write);
        // 2.拷贝 核心思想是边读边写
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        // 3.释放资源:先开的最后关闭
        fos.close();
        fis.close();
    }

    @Test
    @DisplayName("Buffer 拷贝")
    void test_copy_02() throws IOException {
        File read = IOUtil.createTempFile("hello");
        File write = IOUtil.createWritableFile("write.txt");
        long start = System.currentTimeMillis();
        // 1.创建对象
        FileInputStream fis = new FileInputStream(read);
        FileOutputStream fos = new FileOutputStream(write);
        // 2.拷贝
        int len;
        byte[] bytes = new byte[1024 * 1024 * 5];
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        // 3.释放资源
        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
