package edu.note.java.net.simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jackylee
 * @date 2026-04-19 15:15
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080); // 监听指定端口
        System.out.println("server is running...");

        while (true) {
            Socket sock = ss.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }

}
