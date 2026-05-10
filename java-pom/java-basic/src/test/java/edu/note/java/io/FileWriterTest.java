package edu.note.java.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * FileWriter
 *
 * 第一步：创建对象
 * public FileWriter(File file[, boolean append]) 创建字符输出流关联本地文件, [是否续写]
 * public FileWriter(String pathname[, boolean append]) 创建字符输出流关联本地文件, [是否续写]
 *
 * 第二步：读取数据
 * void write(int c) 写单字符
 * void write(String str[, int off, int len]) 写字符串，[起始位置，结束位置]
 * void write(char[] cbuf[, int off, int len]) 写字符数组，[起始位置，结束位置]
 *
 * 第三步：释放资源
 * public void close() 释放资源/关流
 * 
 * @author jackylee
 * @date 2026-05-10 15:21
 */
public class FileWriterTest {

    @Test
    @DisplayName("FileWriter 示例")
    void test_write_01() throws IOException {
        // '我' 25105

        File write = IOUtil.createWritableFile("write.txt");
        FileWriter fw = new FileWriter(write, true);

        fw.write('\r');
        fw.write('\n');
        fw.write(25105);
        fw.write("Hello, world");
        char[] chars = { 'a', '\uD83D', '\uDC4B', 'c', '我' };
        fw.write(chars);
        fw.close();

    }

    @Test
    @DisplayName("FileReader 存在缓冲区")
    void test04() throws IOException {
        File read = IOUtil.createTempFile("read");
        File write = IOUtil.createWritableFile("write.txt");

        Reader fr = new FileReader(read);
        fr.read();// 会把文件中的数据放到缓冲区当中

        // 清空文件
        Writer fw = new FileWriter(write);

        // 正确答案：
        // 但是只能读取缓冲区中的数据，文件中剩余的数据无法再次读取
        int ch;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }

        fw.close();
        fr.close();

    }

    @Test
    @DisplayName("flush 和 close 的区别")
    void test05() throws IOException {
        File write = IOUtil.createWritableFile("write.txt");
        FileWriter fw = new FileWriter(write);
        fw.write("An Apple a day,");
        fw.write("Keep the doctor away.");
        // flush 后写到文件中
        fw.flush();
        fw.write("海的那边");
        fw.write("是敌人");
        fw.close();
    }
}
