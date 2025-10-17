package edu.note.socket.p03_文件上传_txt文件不行;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class FileTest {
    public static void main(String[] args) throws IOException {
        test02();
    }
    public static void test01() throws IOException{
        // 测试是否可以正确写入
        FileInputStream fis = new FileInputStream("mynet/upload.txt");
        FileOutputStream fos = new FileOutputStream("mynet/d2.txt");
        int b;
        while ((b = fis.read())!= -1){
            fos.write(b);
        }
        fos.close();
        fis.close();
        System.out.println("输出成功");
    }
    public static void test02() throws IOException {

        // 读取文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("mynet/upload.txt"));
        byte[] bytes = new byte[1024];
        int len;
        System.out.println("开始读入");
        while((len = bis.read(bytes)) != -1){
            
        }
        bis.close(); // 是否要关闭？

    }
}
