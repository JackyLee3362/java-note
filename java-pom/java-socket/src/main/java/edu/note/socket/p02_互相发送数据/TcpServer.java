package edu.note.socket.p02_互相发送数据;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        // 把InputStream变成字符流
        InputStreamReader isr = new InputStreamReader(is);
        System.out.println("开始接收");
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }
        os.write("收到数据".getBytes());
        socket.close(); // 为什么没有这段代码会报错？
        ss.close();

    }
}
