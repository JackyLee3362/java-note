package edu.note.java.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/1 上午12:07
 */
@Slf4j
public class ZipUtil {


    /*
     *   作用：获取src里面的每一个文件，变成ZipEntry对象，放入到压缩包当中
     *   参数一：数据源
     *   参数二：压缩流
     *   参数三：压缩包内部的路径
     * */
    public static void ZipDir(File src, ZipOutputStream zos, String name) throws IOException {
        // 1.进入src文件夹
        File[] files = src.listFiles();
        // 2.遍历数组
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                // 3.判断-文件，变成ZipEntry对象，放入到压缩包当中
                ZipEntry entry = new ZipEntry(name + "/" + file.getName());
                zos.putNextEntry(entry);
                // 读取文件中的数据，写到压缩包
                FileInputStream fis = new FileInputStream(file);
                int len;
                byte[] bytes = new byte[1024];
                while ((len = fis.read(bytes)) != -1) {
                    zos.write(bytes, 0, len);
                }
                fis.close();
                zos.closeEntry();
            } else {
                // 4.判断-文件夹，递归
                ZipDir(file, zos, name + "/" + file.getName());
            }
        }
    }

    /*
     *   作用：压缩
     *   参数一：表示要压缩的文件
     *   参数二：表示压缩包 zip 的位置
     * */
    public static void ZipSingleFile(File src, File dst) throws IOException {
        // 1.创建压缩流关联压缩包
        // 2.创建ZipEntry对象，表示压缩包里面的每一个文件和文件夹
        // 参数：压缩包里面的路径
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dst))) {
            ZipEntry entry = new ZipEntry(src.getName());
            // 3.把ZipEntry对象放到压缩包当中
            zos.putNextEntry(entry);

            // 4.把src文件中的数据写到压缩包当中
            try (FileInputStream fis = new FileInputStream(src)) {
                int len;

                while ((len = fis.read()) != -1) {
                    zos.write(len);
                }
            }
            zos.closeEntry();
        }
    }

    // 定义一个方法用来解压
    public static void unZip(File src, File dst) throws IOException {
        if (!dst.exists()) {
            log.warn("[ UNZIP ] dst is not exist, created.");
            dst.mkdirs();
        }
        // 解压的本质：把压缩包里面的每一个文件或者文件夹读取出来，按照层级拷贝到目的地当中
        // 创建一个解压缩流用来读取压缩包中的数据
        ZipInputStream zip = new ZipInputStream(new FileInputStream(src));
        // 要先获取到压缩包里面的每一个ZipEntry对象
        // 表示当前在压缩包中获取到的文件或者文件夹
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            log.info("[ UNZIP ] {}", entry);
            if (entry.isDirectory()) {
                // 文件夹：需要在目的地dest处创建一个同样的文件夹
                File file = new File(dst, entry.toString());
                file.mkdirs();
            } else {
                // 文件：需要读取到压缩包中的文件，并把他存放到目的地dest文件夹中（按照层级目录进行存放）
                try (FileOutputStream fos = new FileOutputStream(new File(dst, entry.toString()));) {
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = zip.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);
                    }
                }
                // 表示在压缩包中的一个文件处理完毕
                zip.closeEntry();
            }
        }
        zip.close();
    }

}
