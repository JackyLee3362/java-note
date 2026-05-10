package edu.note.java.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import lombok.extern.slf4j.Slf4j;

// 缓冲流_统计四种缓冲流用时
@Slf4j
public class Compare_ReadFileTest {

    public static void main(String[] args) throws IOException {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            s.append("1234567890");
        }
        File src = IOUtil.createTempFile(s.toString());
        File dst = IOUtil.createWritableFile("write.txt");
        // 课程中文件大小：4,588,568,576 字节
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dst);
        BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(src.toPath()));
        BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(dst.toPath()));

        copyByByte(bis, bos);
        copyByBytes(bis, bos);

        copyByByte(fis, fos);
        copyByBytes(fis, fos);
    }

    // 字符流: 单字符读取
    public static void copyByByte(InputStream in, OutputStream out) throws IOException {
        long start = System.currentTimeMillis();
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        long end = System.currentTimeMillis();
        log.info((end - start) / 1000.0 + "秒");
    }

    // 字符流: 多字符读取
    public static void copyByBytes(InputStream in, OutputStream out) throws IOException {
        long start = System.currentTimeMillis();
        byte[] bytes = new byte[8192];
        int len;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();
        log.info((end - start) / 1000.0 + "秒");
    }

}