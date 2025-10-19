package edu.note.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TcpDemo01Basic {

    private static final String host = "127.0.0.1";
    private static final Integer port = 10086;

    public static void main(String[] args) {

        Thread receiver = new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(port);
                Socket socket = ss.accept();
                InputStream is = socket.getInputStream();
                // 把InputStream变成字符流
                InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);
                String s;
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                }
                socket.close();
                ss.close();
            } catch (Exception e) {
                log.error("错误: {}", e);
            }
        });
        Thread sender = new Thread(() -> {
            try {
                Socket socket = new Socket(host, port);
                OutputStream os = socket.getOutputStream();
                os.write("你好\n世界".getBytes());
                os.close();
                socket.close();
            } catch (Exception e) {
                log.error("错误: {}", e);
            }
        });
        receiver.start();
        sender.start();

    }

}
