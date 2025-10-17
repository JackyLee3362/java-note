package edu.note.socket.p03_文件上传_txt文件不行;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        // 创建服务器
        ServerSocket ss = new ServerSocket(10086);
        Socket socket = ss.accept();

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("mynet/download.txt"));
        byte[] bytes = new byte[1024];
        int len;
        while((len = bis.read(bytes)) != -1){
            bos.write(bytes, 0, len);
        }
        bos.close();
        System.out.println("文件写入成功");
        // 发送写请求成功

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("服务器接收文件成功");
        bw.newLine();
        bw.flush();
        socket.close();
        ss.close();
    }
}
