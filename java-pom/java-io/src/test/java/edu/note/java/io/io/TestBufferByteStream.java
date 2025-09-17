package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/30 下午11:59
 */
// IO 缓冲流
public class TestBufferByteStream extends BaseIOTest {

    File f1 = new File(resource, "io/README.md");
    File f2 = new File(resource, "io/README-2.md");

    /*
     * 字节缓冲输入流的构造方法：
     * public BufferedInputStream(InputStream is)
     * 字节缓冲输出流的构造方法：
     * public BufferedOutputStream(OutputStream os)
     **/
    @Test
    @DisplayName("字节缓冲流 单字节读取")
    void test_01() throws IOException {

        // 1.创建缓冲流的对象
        // CharArrayReader
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
        // 2.循环读取并写到目的地
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
        /*
         * 字节缓冲输入流的构造方法：
         * public BufferedInputStream(InputStream is)
         * 字节缓冲输出流的构造方法：
         * public BufferedOutputStream(OutputStream os)
         **/

        // 1.创建缓冲流的对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
        // 2.拷贝（一次读写多个字节）
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
    @DisplayName("字符缓冲流 Reader 行读取")
    void test03() throws IOException {
        /*
         * 字符缓冲输入流：
         * 构造方法：
         * public BufferedReader(Reader r)
         * 特有方法：
         * public String readLine() 读一整行
         **/

        // 1.创建字符缓冲输入流的对象
        BufferedReader br = new BufferedReader(new FileReader(f1));
        // 2.读取数据
        // 细节：
        // readLine在读取时，一次读一整行，
        // 遇到回车换行结束 不会把回车换行读到内存当中

        String line;
        while (((line = br.readLine()) != null)) {
            System.out.println(line);
        }

        // 3.释放资源
        br.close();

    }

    /*
     * 字符缓冲输出流
     * 构造方法：
     * public BufferedWriter(Writer r)
     * 特有方法：
     * public void newLine() 跨平台的换行
     */
    @Test
    @DisplayName("字符缓冲流 Writer 行写入")
    void test04() throws IOException {

        // 1.创建字符缓冲输出流的对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("io/README-03.out.md", true));
        // 2.写出数据
        bw.write("123");
        // 跨平台换行
        bw.newLine();
        bw.write("456");
        bw.newLine();
        // 3.释放资源
        bw.close();
    }
}
