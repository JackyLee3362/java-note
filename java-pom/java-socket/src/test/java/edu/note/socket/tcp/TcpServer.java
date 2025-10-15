package edu.note.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class TcpServer {

    private static final String host = "127.0.0.1";
    private static final Integer port = 10086;

    public static void main(String[] args) {
        

    }

    void server() throws IOException {
        ServerSocket ss = new ServerSocket(port);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        // 把InputStream变成字符流
        InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        // 单字符读取
        // int b;
        // while ((b = br.read()) != -1) {
        // System.out.println((char) b);
        // }
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        socket.close();
        ss.close();
    }

    void client() throws UnknownHostException, IOException {
        Socket socket = new Socket(host, port);
        OutputStream os = socket.getOutputStream();
        os.write("你好\n世界".getBytes());
        os.close();
        socket.close();
    }

}
