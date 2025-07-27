package edu.note.java.io.file;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// public class T1_创建_文件夹_文件
public class IT02_FileCreateDelete extends BaseIOTest {

    File dir = new File(resource, "/file");

    // public boolean createNewFile()      创建一个新的空的文件
    // 1. 如果文件不存在：则创建成功，返回true，否则创建失败，返回 false
    // 2：如果父级路径不存在，抛出异常 IOException
    @Test
    @DisplayName("创建和删除文件夹")
    public void test01() throws IOException {
        File f1 = new File(dir, "CREATE.md");
        Assertions.assertTrue(f1.createNewFile());
        Assertions.assertTrue(f1.delete());
    }

    // public boolean mkdir() 创建单级文件夹
    @Test
    @DisplayName("创建和删除文件夹")
    public void test02() {
        // mkdir
        //  1：windows当中路径是唯一的，如果当前路径已经存在，则创建失败，返回false
        //  2：mkdir方法只能创建单级文件夹，无法创建多级文件夹。
        File d1 = new File(dir, "d02");
        Assertions.assertTrue(d1.mkdir());
        // public boolean delete() 删除文件、空文件夹
        Assertions.assertTrue(d1.delete());

    }


    @Test
    @DisplayName("递归创建文件夹")
    public void test03() throws IOException {

        // public boolean mkdirs() 创建多级文件夹
        // mkdirs   创建多级文件夹（递归创建）
        // 源码阅读（其实就是递归调用 mkdir）
        File d2 = new File(dir, "d03/sub");
        Assertions.assertTrue(d2.mkdirs());
        Assertions.assertTrue(d2.delete());
    }

    // d2
    // ---- d3
    // ---- ---- f3.txt
    // ---- f3.txt

    @Test
    @DisplayName("递归删除文件夹")
    public void test04() throws IOException {
        File d2 = new File(dir, "d04");
        File f2 = new File(d2, "FILE1.md");
        File d3 = new File(d2, "sub");
        File f3 = new File(d3, "FILE2.md");
        d3.mkdirs();
        f2.createNewFile();
        f3.createNewFile();

        Assertions.assertTrue(d2.exists());
        // FileUtil.recursiveDelete(f3);
        FileUtil.recursiveDelete(d2);
        Assertions.assertFalse(d2.exists());
    }

}
