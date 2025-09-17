package edu.note.java.advance.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IT01_GetException {
    /*
     *    JDK7:IO流中捕获异常的写法
     * */

    @Test
    @DisplayName("jdk7")
    void test1() {

        try (FileInputStream fis = new FileInputStream("D:\\itheima\\movie.mp4");
            FileOutputStream fos = new FileOutputStream("myio\\copy.mp4")) {
            // 2.拷贝
            int len;
            byte[] bytes = new byte[1024 * 1024 * 5];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    @DisplayName("jdk9")
    void test2() throws FileNotFoundException {

        /*
         *    JDK9:IO流中捕获异常的写法
         * */

        FileInputStream fis = new FileInputStream("D:\\itheima\\movie.mp4");
        FileOutputStream fos = new FileOutputStream("myio\\copy.mp4");

        // TODO JDK9 语法
        // try (fis; fos) {
        //     // 2.拷贝
        //     int len;
        //     byte[] bytes = new byte[1024 * 1024 * 5];
        //     while ((len = fis.read(bytes)) != -1) {
        //         fos.write(bytes, 0, len);
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }


    }
}
