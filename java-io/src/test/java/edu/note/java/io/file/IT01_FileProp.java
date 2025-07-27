package edu.note.java.io.file;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IT01_FileProp extends BaseIOTest {

    File f1 = new File(resource, "/file/README.md");

    @Test
    @DisplayName("文件类型")
    public void test01() {
        System.out.println(f1);
        System.out.println("是文件夹吗" + f1.isDirectory()); // false
        System.out.println("是文件吗" + f1.isFile()); // true
        System.out.println("是否存在" + f1.exists()); // true
    }

    @Test
    @DisplayName("文件大小（单位是字节）")
    public void test02() {
        long len = f1.length();
        System.out.println(len);
        // 无法获取文件夹的大小
        // todo: 理论是无法获得的，但是 resource 还是能获得文件大小
        File f2 = new File(resource); // 0
        long len2 = f2.length();
        System.out.println(len2);
    }

    @Test
    @DisplayName("GetPath")
    public void test03() {

        // 2.getAbsolutePath 返回文件的绝对路径
        String path1 = f1.getAbsolutePath();
        System.out.println(path1);

        // 3.getPath 返回定义文件时使用的路径
        String path2 = f1.getPath();
        System.out.println(path2);

        File f6 = new File("file/README.md");
        String path3 = f6.getPath();
        System.out.println(path3);
    }

    @Test
    @DisplayName("获取名字")
    public void test04() {
        File f7 = new File("file/README.md");
        String name1 = f7.getName();
        System.out.println(name1);
        // 返回定义时的名字
        File f8 = new File("file");
        String name2 = f8.getName();
        System.out.println(name2);

    }

    @Test
    @DisplayName("获取文件修改时间")
    public void test05() {

        // 5.lastModified  返回文件的最后修改时间（毫秒值）
        System.out.println(f1.lastModified());
        // 如果该文件不存在，返回 0
        File file = new File("file/README.md");
        long time = file.lastModified();
        System.out.println(time);// 1667380952425
    }
}
