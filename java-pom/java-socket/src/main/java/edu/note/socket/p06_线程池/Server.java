package edu.note.socket.p06_线程池;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器。接收服务器的反馈。
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈。


        // 1.创建对象并绑定端口
        ServerSocket ss = new ServerSocket(10086);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        while (true) {
            // 2.等待客户端来连接
            Socket socket = ss.accept();
            pool.submit(new MyRunnable(socket));
        }
    }
}