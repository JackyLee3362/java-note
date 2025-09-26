package edu.note.java.io.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 缓冲流_统计四种缓冲流用时
public class BufferedReaderStatTimeTest {

    private static final String path = "io/a.txt";
    private static final String src = "io/copy.txt";

    public static void main(String[] args) throws IOException {
           /*
                四种方式拷贝文件，并统计各自用时
                用method2 (FileOutputStream/FileInputStream)
                或者method4 (BufferedOutputStream/BufferedInputStream)
           */

        long start = System.currentTimeMillis();
        // method1();
        // method2();          //16.253秒
        // method3();          //95.466秒  // 慢的原因：一个字节一个字节搬运
        // method4();          //17.686秒
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 + "秒");


    }


    // 课程中文件大小：4,588,568,576 字节
    // 字节流的基本流：一次读写一个字节
    public static void method1() throws IOException {
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(src);
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fos.close();
        fis.close();
    }

    // 字节流的基本流：一次读写一个字节数组
    public static void method2() throws IOException {
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(src);
        byte[] bytes = new byte[8192];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        fis.close();
    }

    // 字节流的基本流：一次读写一个字节数组
    public static void method3() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(src));
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        bos.close();
        bis.close();
    }

    // 字节流的基本流：一次读写一个字节数组
    public static void method4() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(src));
        byte[] bytes = new byte[8192];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        bis.close();
    }
}