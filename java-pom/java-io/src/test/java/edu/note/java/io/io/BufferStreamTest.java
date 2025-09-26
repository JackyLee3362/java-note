package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/30 下午11:59
 * @desc
 * 字节缓冲输入流
 * public BufferedInputStream(InputStream is)
 * 字节缓冲输出流
 * public BufferedOutputStream(OutputStream os)
 * 字符缓冲输入流
 * public BufferedReader(Reader r)
 * 字符缓冲输出流
 * public BufferedWriter(Writer r)
 */
// IO 缓冲流
public class BufferStreamTest extends BaseIOTest {

    File read = new File(ioDir, "buffer.read.txt");
    File write = new File(ioDir, "buffer.write.txt");

    /*
     **/
    @Test
    @DisplayName("字节缓冲流 单字节读取")
    void test01() throws IOException {

        // 1.创建缓冲流的对象
        // CharArrayReader TODO
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(read.toPath()));
        BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(write.toPath()));
        // 2.单字节读取/写入
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        // 3.释放资源
        bos.close();
        bis.close();
    }

    @Test
    @DisplayName("字节缓冲流 多字节读取")
    void test02() throws IOException {

        // 1.创建缓冲流的对象
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(read.toPath()));
        BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(write.toPath()));
        // 2.多字节读取/写入
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        // 3.释放资源
        bos.close();
        bis.close();
    }

    @Test
    @DisplayName("字符缓冲流 Reader 行读取 readLine")
    void test03() throws IOException {
        // 1.创建字符缓冲输入流的对象
        BufferedReader br = new BufferedReader(new FileReader(read));
        // 2.读取数据
        // readLine读取一整行
        // 遇到回车换行结束，不会读取

        String line;
        while (((line = br.readLine()) != null)) {
            System.out.println(line);
        }
        // 3.释放资源
        br.close();

    }

    @Test
    @DisplayName("字符缓冲流 Writer 行写入 newLine 跨平台换行")
    void test04() throws IOException {

        // 1.创建字符缓冲输出流的对象
        // BufferedWriter bw = new BufferedWriter(new FileWriter("io/README-03.out.md", true));
        BufferedWriter bw = new BufferedWriter(new FileWriter(write));
        // 2.写出数据
        bw.write("foo");
        // 跨平台换行
        bw.newLine();
        bw.write("bar");
        bw.newLine();
        // 3.释放资源
        bw.close();
    }
}
