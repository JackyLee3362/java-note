package edu.note.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-18 09:30
 */

@Slf4j
public class TcpReceiver implements Runnable {

    private int port;

    public TcpReceiver(int port) {
        this.port = port;
    }

    public void run() {
        try {
            // 1.创建对象ServerSocker，使用 TCP 协议
            ServerSocket ss = new ServerSocket(port);
            // 2.监听客户端的连接
            Socket socket = ss.accept();
            System.out.println("已有客户端连接...");
            // 3.从连接通道中获取输入流读取数据
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            // int b;
            // while ((b = isr.read()) != -1) {
            //     log.info("收到消息: {}", (char) b);
            // }
            String msg;
            while ((msg = br.readLine()) != null) {
                log.info("收到消息: {}", msg);
            }
            // 写回数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("这里是服务器！".getBytes());
            // 4.释放资源
            socket.close();
            ss.close();
        } catch (Exception e) {
            log.error("错误: {}", e);
        }
    }

}
