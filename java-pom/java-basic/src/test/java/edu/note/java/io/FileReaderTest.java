package edu.note.java.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * FileReader
 *
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
 *
 * @author jackylee
 * @date 2026-05-10 15:20
 */
public class FileReaderTest {

    /**
     * 1.读取数据 read()
     * 字符流底层是字节流，默认是逐个字节读取。
     * 如果遇到中文就会一次读取多个，GBK一次读两个字节，UTF-8一次读三个字节
     * 读取后，进行解码并转换成十进制返回
     * 该十进制的数据也表示在字符集上的数字
     * 举例
     * 英文：文件里面二进制数据 0110 0001 read方法进行读取，解码并转成十进制97
     * 中文：文件里面的二进制数据 11100110 10110001 10001001 read方法进行读取，解码并转成十进制27721
     */
    @Test
    @DisplayName("FileReader read 单个字符")
    void test_read_01() throws IOException {

        File file = IOUtil.createTempFile("hello, world");
        Reader reader = new FileReader(file);

        int ch;
        ArrayList<Integer> bufferChar = new ArrayList<>();
        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch);
            bufferChar.add(ch);
        }
        System.out.println();
        System.out.println(bufferChar);

        // 3.释放资源
        reader.close();
    }

    @Test
    @DisplayName("FileReader read 字符数组")
    void test02() throws IOException {

        File file = IOUtil.createTempFile("1😊");
        FileReader fr = new FileReader(file);
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
}
