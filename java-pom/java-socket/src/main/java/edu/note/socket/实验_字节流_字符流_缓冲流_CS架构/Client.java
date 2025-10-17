package edu.note.socket.实验_字节流_字符流_缓冲流_CS架构;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10086);
        // 定义变量
        String s = "中文English";
        Scanner scanner = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        // 实验1：用OutputStream发送
        System.out.println("实验1：用OutputStream发送");
        while ((s = scanner.nextLine()+"\n")!=null && !"886\n".equals(s)) {
            os.write(s.getBytes());
        }
        // 实验2：用OutputStreamWriter发送
        System.out.println("实验2：用OutputStreamWriter发送");
        while ((s = scanner.nextLine()+"\n")!=null && !"886\n".equals(s)) {
            osw.write(s);
            osw.flush(); // 发送给os时需要加
        }
        // 实验3：用BufferedWriter发送
        System.out.println("实验3：用BufferedWriter发送");
        while ((s = scanner.nextLine()+"\n")!=null && !"886\n".equals(s)) {
            bw.write(s);
            bw.flush();
        }
        socket.shutdownOutput(); // 写了一个结束标记
        socket.close();

    }
}
