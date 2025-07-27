package edu.note.java.io.file;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IT05_FileIter extends BaseIOTest {

    File dir = new File(resource, "file");

    @Test
    @DisplayName("列出可用的文件系统根")
    public void test01() {
        File[] arr = File.listRoots();
        System.out.println(Arrays.toString(arr));
    }

    @Test
    @DisplayName("list")
    public void test02() {

        // 1.list()    获取当前该路径下所有内容(仅仅能获取名字)
        String[] arr = dir.list();
        System.out.println(Arrays.toString(arr));

        // 2.list(FilenameFilter filter)  利用文件名过滤器获取当前该路径下所有内容
        // accept方法的形参，依次表示aaa文件夹里面每一个文件或者文件夹的路径
        // 参数一：父级路径
        // 参数二：子级路径
        // 返回值：如果返回值为true，就表示当前路径保留
        //       如果返回值为false，就表示当前路径舍弃不要
        String[] arr3 = dir.list((dir, name) -> new File(dir, name).isFile() && name.endsWith(".md"));
        System.out.println(Arrays.toString(arr3));
    }

    @Test
    @DisplayName("listFile")
    public void test03() {
        // File[] listFiles() 获取当前该路径下所有内容
        // 1. 如果路径不存在或无权限访问，返回null
        // 2. 会同时打印隐藏文件夹
        File[] files = dir.listFiles();
        System.out.println(Arrays.toString(files));
        // File[] listFiles(FileFilter filter)      利用文件名过滤器获取当前该路径下所有内容
        File[] arr1 = dir.listFiles(pathname -> pathname.isFile() && pathname.getName().endsWith(".md"));
        System.out.println(Arrays.toString(arr1));

        // public File[] listFiles(FilenameFilter filter)  利用文件名过滤器获取当前该路径下所有内容
        File[] arr2 = dir.listFiles((dir, name) -> new File(dir, name).isFile() && name.endsWith(".md"));
        System.out.println(Arrays.toString(arr2));
    }

}
