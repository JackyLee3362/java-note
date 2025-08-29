package edu.note.java.io.io.commonIo;

// import org.apache.commons.io.FileUtils;

import java.io.IOException;

public class A01_HandleFile {
    public static void main(String[] args) throws IOException {
        /*
          FileUtils类
                static void copyFile(File srcFile, File destFile)                   复制文件
                static void copyDirectory(File srcDir, File destDir)                复制文件夹
                static void copyDirectoryToDirectory(File srcDir, File destDir)     复制文件夹
                static void deleteDirectory(File directory)                         删除文件夹
                static void cleanDirectory(File directory)                          清空文件夹
                static String readFileToString(File file, Charset encoding)         读取文件中的数据变成成字符串
                static void write(File file, CharSequence data, String encoding)    写出数据

            IOUtils类
                public static int copy(InputStream input, OutputStream output)      复制文件
                public static int copyLarge(Reader input, Writer output)            复制大文件
                public static String readLines(Reader input)                        读取数据
                public static void write(String data, OutputStream output)          写出数据
         */

        //
        // // 文件拷贝
        // File src = new File("myio\\a.txt");
        // File dest = new File("myio\\copy.txt");
        // FileUtils.copyFile(src, dest);
        //
        // // 文件夹拷贝 copyDirectory
        // // 拷贝前 aaa/*
        // // 拷贝后 bbb/*
        // File src2 = new File("myio\\aaa");
        // File dest2 = new File("myio\\bbb");
        // FileUtils.copyDirectory(src2, dest2);
        //
        // // 文件夹拷贝 copyDirectoryToDirectory
        // // 拷贝前 aaa/*
        // // 拷贝后 bbb/aaa/*
        // File src3 = new File("myio\\aaa");
        // File dest3 = new File("myio\\bbb");
        // FileUtils.copyDirectoryToDirectory(src3, dest3);
        //
        // // 文件夹清空
        // File src4 = new File("myio\\bbb");
        // FileUtils.cleanDirectory(src4);
        //
        // // 文件夹删除
        // File src5 = new File("myio\\bbb");
        // FileUtils.deleteDirectory(src5);
        //

    }
}
