package edu.note.socket.p05_多线程上传;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.FutureTask;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1.创建对象并绑定端口
        ServerSocket ss = new ServerSocket(10086);
        while (true) {
            // 2.等待客户端来连接
            Socket socket = ss.accept();
            ServerThreadCallable st = new ServerThreadCallable(socket);
            FutureTask<Object> ft = new FutureTask<>(st);
            Thread t = new Thread(ft);
            t.start();
        }
        // ss.close();
    }
}