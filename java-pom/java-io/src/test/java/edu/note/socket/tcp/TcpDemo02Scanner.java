package edu.note.socket.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * TCP协议，发送数据，单工
 */
@Slf4j
public class TcpDemo02Scanner {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 10000;
        Runnable sender = () -> {
            try {
                // 1.创建Socket对象
                // 在创建对象的同时会连接服务端
                // 如果连接不上，代码会报错
                Socket socket = new Socket("127.0.0.1", port);

                // 2.可以从连接通道中获取输出流
                Scanner scanner = new Scanner(System.in);
                
                while (true) {
                    OutputStream os = socket.getOutputStream();
                    System.out.println("请输入您要发送的信息");
                    String str = scanner.nextLine();
                    if ("886".equals(str)) {
                        break;
                    }
                    os.write((str+"\n").getBytes());
                    os.flush();
                }
                // 3.释放资源
                socket.close();
            } catch (Exception e) {
                log.error("错误: {}", e);
            }
        };
        new Thread(new TcpReceiver(port), "接收方").start();
        new Thread(sender, "发送方").start();
    }
}
