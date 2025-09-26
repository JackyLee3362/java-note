package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 上午12:01
 */
// 转换流
public class TransStreamTest extends BaseIOTest {

    File read = new File(ioDir, "trans.read.gbk.txt");
    File write = new File(resource, "trans.write.txt");

    // 利用转换流按照指定字符编码读取
    @Test
    @DisplayName("字节流to字符流（jdk11前）")
    void test_01_before_jdk11() throws IOException {
        // JDK11 之前的方案
        // 1.创建对象并指定字符编码
        InputStreamReader isr = new InputStreamReader(Files.newInputStream(read.toPath()), Charset.forName("GBK"));
        // 2.读取数据
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }
        // 3.释放资源
        isr.close();
    }

    @Test
    @DisplayName("字节流to字符流（jdk11后）")
    void test_01_after_jdk11() throws IOException {
        // JDK11 之后才有这个构造方法
        FileReader fr = new FileReader(read);// , Charset.forName("GBK"));
        // 2.读取数据
        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        // 3.释放资源
        fr.close();

    }

    @Test
    @DisplayName("字符流to字节流（jdk11前）")
    void test_02_before_jdk11() throws IOException {

        // 1.创建转换流的对象
        // OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f2),
        // "GBK");
        OutputStreamWriter osw = new OutputStreamWriter(Files.newOutputStream(write.toPath()));
        // 2.写出数据
        osw.write("你好你好");
        // 3.释放资源
        osw.close();

    }

    @Test
    @DisplayName("字符流to字节流（jdk11后）")
    void test_02_after_jdk11() throws IOException {
        // JDK11之后的构造方法
        // FileWriter fw = new FileWriter(f2, Charset.forName("GBK"));
        FileWriter fw = new FileWriter(write); // Charset.forName("GBK"));
        fw.write("你好你好");
        fw.close();
    }

    @Test
    @DisplayName("GBK转UTF8（jdk11前）")
    void test_03_before_jdk11() throws IOException {
        // InputStreamReader isr = new InputStreamReader(new FileInputStream(f1),
        // "GBK");
        InputStreamReader isr = new InputStreamReader(Files.newInputStream(read.toPath()));
        // OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f2),
        // StandardCharsets.UTF_8);
        OutputStreamWriter osw = new OutputStreamWriter(Files.newOutputStream(write.toPath()));

        int b;
        while ((b = isr.read()) != -1) {
            osw.write(b);
        }

        osw.close();
        isr.close();
    }

    @Test
    @DisplayName("GBK转UTF8（jdk11后）")
    void test03() throws IOException {

        // 2.替代方案
        FileReader fr = new FileReader(read); // , Charset.forName("GBK"));
        FileWriter fw = new FileWriter(write); // StandardCharsets.UTF_8);

        int b;
        while ((b = fr.read()) != -1) {
            fw.write(b);
        }
        fw.close();
        fr.close();

    }

}