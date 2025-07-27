package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 上午12:02
 */
public class IT07_ZipStream extends BaseIOTest {

    File dir = new File(resource, "io/");


    @Test
    @DisplayName("解压")
    public void test01() throws IOException {

        // 1.创建一个File表示要解压的压缩包
        File src = new File(dir, "README-07.zip");
        // 2.创建一个File表示解压的目的地
        File dst = new File(dir, "README-07");
        // 调用方法
        ZipUtil.unZip(src, dst);

    }

    @Test
    @DisplayName("压缩流：压缩单文件")
    public void test02() throws IOException {

        // 压缩流:把单个文件打包成一个压缩包
        // 1.创建File对象表示要压缩的文件
        File src = new File(dir, "README.md");
        // 2.创建File对象表示压缩包的位置
        File dst = new File(dir, "README-07b.zip");
        // 3.调用方法用来压缩
        ZipUtil.ZipSingleFile(src, dst);
    }

    @Test
    @DisplayName("压缩文件夹")
    public void test03() throws IOException {
        // 压缩流:把文件夹压缩成一个压缩包
        // 1.创建File对象表示要压缩的文件夹
        File src = new File(dir, "README-07");
        // 2.创建File对象表示压缩包的路径
        File dst = new File(dir, "README-07b.zip");
        // 3.创建压缩流关联压缩包
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dst))) {
            // 4.获取src里面的每一个文件，变成ZipEntry对象，放入到压缩包当中
            ZipUtil.ZipDir(src, zos, src.getName());
        }
    }

}
