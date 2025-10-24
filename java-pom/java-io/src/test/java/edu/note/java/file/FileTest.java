package edu.note.java.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.java.io.BaseIOTest;
import edu.note.java.io.file.FileUtil;

public class FileTest extends BaseIOTest {

    @Test
    @DisplayName("文件属性")
    void test01() {
        File f1 = new File("README.md");
        assertFalse(f1.exists());

        // 获取文件还是目录
        assertFalse(f1.isFile());
        assertFalse(f1.isDirectory());

        // 获取可读可写可执行权限
        assertFalse(f1.canRead());
        assertFalse(f1.canWrite());
        assertFalse(f1.canExecute());
        // 获取名字
        assertEquals("README.md", f1.getName());
        // 获取路径
        assertInstanceOf(String.class, f1.getPath());
        assertInstanceOf(String.class, f1.getAbsolutePath());
        // 最后修改时间
        assertInstanceOf(Long.class, f1.lastModified());

    }

    @Test
    @DisplayName("列出可用的文件系统根")
    void test02() {
        File[] arr = File.listRoots();
        assertNotNull(arr);
    }

    @Test
    @DisplayName("遍历")
    void test03() {
        File dir = new File(resource, "file");

        // 遍历（非递归）
        String[] arr = dir.list();
        assertNotNull(arr);

        // 过滤
        String[] arr3 = dir.list((dir_, name) -> new File(dir_, name).isFile() && name.endsWith(".md"));
        assertNotNull(arr3);

        // 遍历（非递归）
        File[] files = dir.listFiles();
        assertNotNull(files);
        // 过滤
        File[] arr1 = dir.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(".md"));
        System.out.println(Arrays.toString(arr1));
    }

    // public boolean createNewFile() 创建一个新的空的文件
    // 1. 如果文件不存在：则创建成功，返回true，否则创建失败，返回 false
    // 2：如果父级路径不存在，抛出异常 IOException
    @Test
    @DisplayName("创建/删除文件")
    void test04() throws IOException {
        File dir = new File(resource, "file");
        File f1 = new File(dir, "CREATE.md");
        assertTrue(f1.createNewFile());
        assertTrue(f1.delete());

        // mkdir
        // 1：windows当中路径是唯一的，如果当前路径已经存在，则创建失败，返回false
        // 2：mkdir方法只能创建单级文件夹，无法创建多级文件夹。
        // public boolean mkdir() 创建单级文件夹
        File d1 = new File(dir, "temp-dir");
        assertTrue(d1.mkdir());
        // 删除文件、空文件夹
        assertTrue(d1.delete());
    }

    @Test
    @DisplayName("递归创建文件夹")
    void test05() throws IOException {
        File dir = new File(resource, "file");

        // public boolean mkdirs() 创建多级文件夹
        // mkdirs 创建多级文件夹（递归创建）
        // 源码阅读（其实就是递归调用 mkdir）
        File d2 = new File(dir, "d03/sub");
        assertTrue(d2.mkdirs());
        assertTrue(d2.delete());
    }

    // d2
    // ---- d3
    // ---- ---- f3.txt
    // ---- f3.txt

    @Test
    @DisplayName("递归删除文件夹")
    void test06() throws IOException {
        File dir = new File(resource, "file");
        File d2 = new File(dir, "d04");
        File f2 = new File(d2, "FILE1.md");
        File d3 = new File(d2, "sub");
        File f3 = new File(d3, "FILE2.md");
        d3.mkdirs();
        f2.createNewFile();
        f3.createNewFile();

        assertTrue(d2.exists());
        // FileUtil.recursiveDelete(f3);
        FileUtil.recursiveDelete(d2);
        assertFalse(d2.exists());
    }
}
