package edu.note.java.io.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * @desc 递归拷贝文件夹
 */
public class CopyDirRecursiveTest {

    @Test
    void test01() throws IOException {
        // 拷贝一个文件夹，考虑子文件夹

        // 1.创建对象表示数据源
        File src = new File("D:\\aaa\\src");
        // 2.创建对象表示目的地
        File dest = new File("D:\\aaa\\dest");

        // 3.调用方法开始拷贝
        copyDir(src, dest);
    }

    /*
     * 作用：拷贝文件夹
     * 参数一：数据源
     * 参数二：目的地
     *
     */
    private static void copyDir(File src, File dest) throws IOException {
        dest.mkdirs(); // 如果不存在就创建
        // 递归
        // 1.进入数据源
        File[] files = src.listFiles();
        // 2.遍历数组
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                // 3.判断文件，拷贝
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(dest, file.getName()));
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
                fos.close();
                fis.close();
            } else {
                // 4.判断文件夹，递归
                copyDir(file, new File(dest, file.getName()));
            }
        }
    }
}
