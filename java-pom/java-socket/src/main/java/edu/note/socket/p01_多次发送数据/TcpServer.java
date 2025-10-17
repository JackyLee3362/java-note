package edu.note.socket.p01_多次发送数据;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        // 把InputStream变成字符流
        InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        String s;
        System.out.println("开始接收");
        // while ((s = br.readLine()) != null) {
        //     System.out.println("开始接受下一行");
        //     System.out.println(s);
        //     if ("886".equals(s)) {
        //         break;
        //     }
        // }
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }
        // socket.close();
        ss.close();

    }
}
