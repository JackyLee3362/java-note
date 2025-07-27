package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/11/29 下午3:51
 */
public class IT01_ByteStream extends BaseIOTest {

    File f1 = new File(resource, "io/README.md");
    File f2 = new File(resource, "io/README-01.out.md");

    @Test
    @DisplayName("循环读取")
    public void test01() throws IOException {
        FileInputStream fis = new FileInputStream(f1);
        // 2.循环读取
        int b;
        while ((b = fis.read()) != -1) {
            System.out.print((char) b);
        }
        // 3.释放资源
        fis.close();

    }

    @Test
    @DisplayName("Buffer 读取")
    public void test02() throws IOException {
        FileInputStream fis = new FileInputStream(f1);

        // 2.读取数据
        byte[] bytes = new byte[4];
        // 一次读取多个字节数据，具体读多少，跟数组的长度有关
        // 返回值：本次读取到了多少个字节数据
        int len;
        String str;
        while ((len = fis.read(bytes)) != -1) {
            System.out.println("读取长度为 " + len);// 3
            str = new String(bytes, 0, len);
            System.out.println(str);
        }
        // 3.释放资源
        fis.close();
    }


    @Test
    @DisplayName("Question: Emoji/中文 如何读取")
    public void test07() throws IOException {
        // code point>=65536 怎么办？
        // 因为是字节流读取，所以无法正确显示
        File f1 = new File(resource, "io/EMOJI.md");
        FileInputStream f = new FileInputStream(f1);
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
    public void test_write_01() throws IOException {
        FileOutputStream fos = new FileOutputStream(f1);
        // 先写一个 'a'
        fos.write(97);
        // 再写一个换行 '\r\n'
        fos.write("\r\n".getBytes());
        // 再写一个 'bc'
        byte[] bytes = {97, 98, 99, 100, 101};
        fos.write(bytes, 1, 2);
        fos.close();
        Assertions.assertEquals(5, f1.length());
    }

    @Test
    @DisplayName("未释放资源无法删除文件")
    public void test_write_02() throws IOException {
        FileOutputStream fos = new FileOutputStream(f1);
        fos.write(97);
        Assertions.assertFalse(f1.delete());
        fos.close();
        Assertions.assertTrue(f1.delete());
    }

    @Test
    @DisplayName("拷贝文件")
    public void test_copy_01() throws IOException {

        FileInputStream fis = new FileInputStream(f1);
        FileOutputStream fos = new FileOutputStream(f2);
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
    public void test_copy_02() throws IOException {
        long start = System.currentTimeMillis();
        // 1.创建对象
        FileInputStream fis = new FileInputStream(f1);
        FileOutputStream fos = new FileOutputStream(f2);
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
