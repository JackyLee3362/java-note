package edu.note.socket.实验_字节流_字符流_缓冲流_CS架构;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("开始启动");
        ServerSocket ss = new ServerSocket(10086);
        Socket socket = ss.accept();
        // 定义接收变量
        int b;
        String s;
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // // 实验1：用InputStream接收
        //        可以接收OutputStream
        //        可以接收OutStreamWriter
        //        可以接收BufferedReader
        // while ((b = is.read()) != -1) {
        //     System.out.print((char) b);
        // }
        // // 实验2：用InputStreamReader接收
        //        可以接收OutputStream
        //        可以接收OutStreamWriter
        //        可以接收BufferedReader
        // while ((b = isr.read()) != -1) {
        //     System.out.print((char) b);
        // }
        // // 实验3：用BufferReader接收
        //        可以接收OutputStream
        //        可以接收OutStreamWriter
        //        可以接收BufferedReader
        while ((s = br.readLine())!=null){
            System.out.println(s);
        }
        socket.close(); // 为什么没有这段代码会报错？
        ss.close();
    }
}
