package edu.note.java.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileTest {

    @Test
    @DisplayName("文件属性测试")
    void test_01() throws IOException {
        // 创建临时文件
        File file = IOUtil.createTempFile("buffer read content");
        IOUtil.statFileInfo(file);
    }

    @Test
    @DisplayName("目录属性测试")
    void test_02() throws IOException {
        // 创建临时文件
        File file = IOUtil.createTempDirectory("test");
        IOUtil.statFileInfo(file);
    }

    @Test
    @DisplayName("遍历目录")
    void test03() {
        // 文件根系统
        File[] roots = File.listRoots();
        File dir = roots[0];
        log.info("文件系统根: {}", dir.toString());

        // 遍历目录中所有（非递归）
        log.info("子目录是: {}", dir.list().toString());
        // 过滤
        String[] arr3 = dir.list((dir_, name) -> new File(dir_, name).isFile() && name.endsWith(".md"));
        assertNotNull(arr3);

        // 遍历目录中的文件（非递归）
        File[] files = dir.listFiles();
        assertNotNull(files);
        // 过滤
        File[] arr1 = dir.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(".md"));
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    @DisplayName("创建/删除文件")
    void test04() throws IOException {
        File dir = IOUtil.createTempDirectory("test");

        // createNewFile() 创建一个新的空的文件
        // 1. 如果文件不存在：则创建成功，返回true，否则创建失败，返回 false
        // 2：如果父级路径不存在，抛出异常 IOException
        File file = new File(dir, "test2026.txt");
        file.createNewFile();
        file.delete();

        // mkdir
        // 1：windows当中路径是唯一的，如果当前路径已经存在，则创建失败，返回false
        // 2：mkdir方法只能创建单级文件夹，无法创建多级文件夹。
        // mkdir() 创建单级文件夹
        File d1 = new File(dir, "temp");
        d1.mkdir();
        d1.delete();
    }

    @Test
    @DisplayName("递归创建文件夹")
    void test05() throws IOException {
        File dir = IOUtil.createTempDirectory("test");

        // mkdirs() 创建多级文件夹
        // mkdirs 创建多级文件夹（递归创建） 源码阅读（其实就是递归调用 mkdir）
        File sub = new File(dir, "sub1/sub2");
        sub.mkdirs();
        sub.delete();
    }

}
