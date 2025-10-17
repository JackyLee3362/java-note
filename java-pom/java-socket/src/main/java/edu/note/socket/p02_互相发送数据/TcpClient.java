package edu.note.socket.p02_互相发送数据;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    // 需求，互相发送和接收
    public static void main(String[] args) throws IOException {
        // 发送端
        Socket socket = new Socket("127.0.0.1", 10086);
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        String s;
        int b;
        do {
            System.out.println("请输入文字");
            s = scanner.nextLine() + "\n";
            os.write(s.getBytes());
            // 从服务端接受数据
        } while (!"886\n".equals(s));
        socket.shutdownOutput(); // 写了一个结束标记

        // 接受服务端的数据
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }
        socket.close();

    }
}
