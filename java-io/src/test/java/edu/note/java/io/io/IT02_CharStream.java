package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IT02_CharStream extends BaseIOTest {

    /*
     * 第一步：创建对象
     * public FileReader(File file) 创建字符输入流关联本地文件
     * public FileReader(String pathname) 创建字符输入流关联本地文件
     * 
     * 第二步：读取数据
     * public int read() 读取数据，读到末尾返回-1
     * public int read(char[] buffer) 读取多个数据，读到末尾返回-1
     * 
     * 第三步：释放资源
     * public void close() 释放资源/关流
     */
    File f1 = new File(resource, "io/README.cn.md");
    File f2a = new File(resource, "io/README-02a.out.md");
    File f2b = new File(resource, "io/README-02b.out.md");

    @Test
    @DisplayName("FileReader read空参")
    void test_read_01() throws IOException {

        // 1.创建对象并关联本地文件
        Reader fr = new FileReader(f1);
        // 2.读取数据 read()
        // 字符流的底层也是字节流，默认也是一个字节一个字节的读取的。
        // 如果遇到中文就会一次读取多个，GBK一次读两个字节，UTF-8一次读三个字节

        // 1.read():默认也是一个字节一个字节的读取的,如果遇到中文就会一次读取多个
        // 2.在读取之后，方法的底层还会进行解码并转成十进制。
        // 最终把这个十进制作为返回值
        // 这个十进制的数据也表示在字符集上的数字
        // 英文：文件里面二进制数据 0110 0001 read方法进行读取，解码并转成十进制97
        // 中文：文件里面的二进制数据 11100110 10110001 10001001 read方法进行读取，解码并转成十进制27721

        int ch;
        ArrayList<Integer> bufferCh = new ArrayList<>();
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
            bufferCh.add(ch);
        }
        System.out.println();
        System.out.println(bufferCh);

        // 3.释放资源
        fr.close();
    }

    @Test
    @DisplayName("FileReader read 带参数")
    void test02() throws IOException {
        /*
         * 第一步：创建对象
         * public FileReader(File file) 创建字符输入流关联本地文件
         * public FileReader(String pathname) 创建字符输入流关联本地文件
         * 
         * 第二步：读取数据
         * public int read() 读取数据，读到末尾返回-1
         * public int read(char[] buffer) 读取多个数据，读到末尾返回-1
         * 
         * 第三步：释放资源
         * public void close() 释放资源/关流
         */

        // 1.创建对象
        FileReader fr = new FileReader(f1);
        // 2.读取数据
        char[] chars = new char[2];
        int len;
        // read(chars)：读取数据，解码，强转三步合并了，把强转之后的字符放到数组当中
        // 空参的read + 强转类型转换
        while ((len = fr.read(chars)) != -1) {
            // 把数组中的数据变成字符串再进行打印
            System.out.print(new String(chars, 0, len));
        }
        System.out.println();
        // 这里有个很有意思的地方，我在文件中写的是 "1😊"
        // 然后我的字符数组长度是2，第一次读取只会读取 [1, '\u0000'] len=1，并不会把😊的第一个字符读取进来
        // 第二次读取的时候才会变成 ['\uD83D','\uDE0A'] 并输出😊
        // 我现在试一下改为字符数组长度为1
        // 😊会一个一个字符读取，但是控制台输出是2个字符都输出以后才输出（有点连字符的感觉？）
        //
        // 3.释放资源
        fr.close();

    }

    @Test
    @DisplayName("FileWriter Demo")
    void test_write_01() throws IOException {
        /*
         * 第一步：创建对象
         * public FileWriter(File file) 创建字符输出流关联本地文件
         * public FileWriter(String pathname) 创建字符输出流关联本地文件
         * public FileWriter(File file, boolean append) 创建字符输出流关联本地文件，续写
         * public FileWriter(String pathname, boolean append) 创建字符输出流关联本地文件，续写
         * 
         * 第二步：读取数据
         * void write(int c) 写出一个字符
         * void write(String str) 写出一个字符串
         * void write(String str, int off, int len) 写出一个字符串的一部分
         * void write(char[] cbuf) 写出一个字符数组
         * void write(char[] cbuf, int off, int len) 写出字符数组的一部分
         * 
         * 第三步：释放资源
         * public void close() 释放资源/关流
         * 
         * 
         * '我' 25105
         */

        FileWriter fw = new FileWriter(f2a, true);

        fw.write('\r');
        fw.write('\n');
        fw.write(25105);
        fw.write("Hello, world");
        char[] chars = { 'a', '\uD83D', '\uDC4B', 'c', '我' };
        fw.write(chars);
        fw.close();

    }

    @Test
    @DisplayName("验证FileReader缓冲区")
    void test04() throws IOException {

        Reader fr = new FileReader(f2b);
        fr.read();// 会把文件中的数据放到缓冲区当中

        // 清空文件
        Writer fw = new FileWriter(f2b);

        // 正确答案：
        // 但是只能读取缓冲区中的数据，文件中剩余的数据无法再次读取
        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.println((char) ch);
        }

        fw.close();
        fr.close();

    }

    @Test
    @DisplayName("flush 和 close 的区别")
    void test05() throws IOException {
        File file = new File(resource, "io/FLUSH.md");
        FileWriter fw = new FileWriter(file);
        fw.write("An Apple a day,");
        fw.write("Keep the doctor away.");
        // flush 后写到文件中
        fw.flush();
        fw.write("海的那边");
        fw.write("是敌人");
        fw.close();
    }
}