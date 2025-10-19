package edu.note.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

/**
 * TCP 协议双向发送数据
 * @author jackylee
 * @date 2025-10-19 12:44
 */
@Slf4j
public class TcpDemo03Chat {
    public static void main(String[] args) throws InterruptedException {
        int port = 10000;
        Runnable client = () -> {
            // 客户端：发送一条数据，接收服务端反馈的消息并打印
            // 服务器：接收数据并打印，再给客户端反馈消息

            // 1. 创建Socket对象并连接服务端
            Socket socket;
            try {
                socket = new Socket("127.0.0.1", port);
                // 2. 写出数据
                String str = "服务器，服务器，收到请回答";
                OutputStream os = socket.getOutputStream();
                os.write(str.getBytes());

                // 写出一个结束标记
                socket.shutdownOutput();

                // 3. 接收服务端回写的数据
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                int b;
                String msg;
                while ((msg = br.readLine()) != null) {
                    log.info("收到消息: {}", msg);
                }

                // 4. 释放资源
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(new TcpReceiver(port), "服务端").start();
        Thread.sleep(500);
        new Thread(client, "客户端").start();

    }

}
