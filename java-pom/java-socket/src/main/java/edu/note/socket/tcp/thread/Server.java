package edu.note.socket.tcp.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * TODO: 需要手动创建 io/server 和 io/client 文件夹
 * 
 * @author jackylee
 * @date 2025-10-20 23:56
 */
public class Server {
    private static String res;

    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器。接收服务器的反馈。
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈。
        res = Server.class.getClassLoader().getResource(".").getPath();

        // 1.创建对象并绑定端口
        try (ServerSocket server = new ServerSocket(10005);) {
            while (true) {
                // 2.等待客户端来连接
                Socket socket = server.accept();

                // 开启一条线程
                // 一个用户就对应服务端的一条线程
                new Thread(new MyRunnable(socket)).start();
            }
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
                String filename = UUID.randomUUID().toString().replace("-", "");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(res, "io/server/"+filename)));
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