package edu.note.java.socket.tcp.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
    static String res;
    static Random RANDOM = new Random();

    public static void main(String[] args) {
        res = Client.class.getClassLoader().getResource(".").getPath();
        // for (int i = 0; i < 10; i++) {
        //     new Thread(runnable).start();
        // }
        new Thread(runnable).run();
    }

    private static Runnable runnable = () -> {
        try (Socket socket = new Socket("127.0.0.1", 10005);) {
            // 1. 创建Socket对象，并连接服务器

            // 2.读取本地文件中的数据，并写到服务器当中
            // 生成数据
            File file = new File(res, "io/client/" + UUID.randomUUID().toString());
            BufferedWriter generator = new BufferedWriter(new FileWriter(file));
            generator.write(UUID.randomUUID().toString());
            generator.close();

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }

            // 往服务器写出结束标记
            socket.shutdownOutput();

            // 3.接收服务器的回写数据
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = br.readLine();
            System.out.println(line);

            // 4.释放资源
            socket.close();
        } catch (Exception e) {
            log.error("错误: {}", e);
        }
    };
}