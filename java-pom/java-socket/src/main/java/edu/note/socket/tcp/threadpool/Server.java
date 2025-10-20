package edu.note.socket.tcp.threadpool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器。接收服务器的反馈。
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈。

        // 创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3, // 核心线程数量
                16, // 线程池总大小
                60, // 空闲时间
                TimeUnit.SECONDS, // 空闲时间（单位）
                new ArrayBlockingQueue<>(2), // 队列
                Executors.defaultThreadFactory(), // 线程工厂，让线程池如何创建线程对象
                new ThreadPoolExecutor.AbortPolicy()// 阻塞队列
        );

        // 1.创建对象并绑定端口
        ServerSocket ss = new ServerSocket(10000);

        while (true) {
            // 2.等待客户端来连接
            Socket socket = ss.accept();

            // 开启一条线程
            // 一个用户就对应服务端的一条线程
            // new Thread(new MyRunnable(socket)).start();
            pool.submit(new MyRunnable(socket));
        }

    }

    static class MyRunnable implements Runnable {

        Socket socket;

        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 3.读取数据并保存到本地文件中
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                String name = UUID.randomUUID().toString().replace("-", "");
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream("mysocketnet\\serverdir\\" + name + ".jpg"));
                int len;
                byte[] bytes = new byte[1024];
                while ((len = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, len);
                }
                bos.close();
                // 4.回写数据
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("上传成功");
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 5.释放资源
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}