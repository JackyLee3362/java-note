package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        String s = "聊天室开启";
        byte[] bytes = s.getBytes();
        InetAddress ia = InetAddress.getByName("127.0.0.1");
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, ia, 10086);
        Scanner sc = new Scanner(System.in); // InputStream 字节输入流
        while (true) {
            System.out.println("请输入需要发送的文字，按回车发送");
            s = sc.nextLine();
            dp.setData(s.getBytes());
            ds.send(dp);
            if ("886".equals(s)) {
                break;
            }
        }
        System.out.println("聊天室结束");
        ds.close();
    }
}
