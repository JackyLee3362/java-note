package edu.note.socket.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-19 12:58
 */
@Slf4j
public class TcpDemo04UploadFile {
    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        // 类路径
        // TODO 放入 file 中
        res = TcpDemo04UploadFile.class.getClassLoader().getResource(".").getPath();
        new Thread(server, "服务端").start();
        Thread.sleep(500);
        new Thread(client, "客户端").start();
    }

    private static String res;
    private static Runnable client = () -> {

        // 客户端：将本地文件上传到服务器。接收服务器的反馈。
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈。

        try {
            // 1. 创建Socket对象，并连接服务器
            Socket socket = new Socket("127.0.0.1", 10086);

            // 2.读取本地文件中的数据，并写到服务器当中

            File src = new File(res, "/io/foo.src.txt");
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                // 必须要有这个
                bos.flush();
            }

            // 往服务器写出结束标记
            log.info("客户端传输结束");
            socket.shutdownOutput();

            // 3.接收服务器的回写数据
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = br.readLine();
            log.info("收到数据: {}", line);

            // 4.释放资源
            socket.close();
            bis.close();
        } catch (Exception e) {
            log.error("错误: {}", e);
        }
    };
    private static Runnable server = () -> {

        // 客户端：将本地文件上传到服务器。接收服务器的反馈。
        // 服务器：接收客户端上传的文件，上传完毕之后给出反馈。

        try {
            // 1.创建对象并绑定端口
            ServerSocket ss = new ServerSocket(10086);

            // 2.等待客户端来连接
            Socket socket = ss.accept();

            // 3.读取数据并保存到本地文件中
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            // 一般使用 uuid 重命名
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(new File(res, "io/foo.dst.txt")));
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

            // 5.释放资源
            socket.close();
            ss.close();
        } catch (IOException e) {
            log.error("错误: {}", e);
        }
    };
}
