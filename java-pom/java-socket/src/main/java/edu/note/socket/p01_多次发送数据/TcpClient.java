package edu.note.socket.p01_多次发送数据;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    // 需求，多次发送和接收
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10086);
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        String s;
        do {
            System.out.println("请输入文字");
            s = scanner.nextLine() + "\n";
            os.write(s.getBytes());
        } while (!"886\n".equals(s));
        // os.close();
        socket.close();

    }
}
