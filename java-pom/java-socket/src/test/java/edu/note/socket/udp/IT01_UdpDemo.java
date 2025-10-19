package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * UDP 单播与广播
 * 
 * @author jackylee
 * @date 2025-10-18 07:32
 */
@Slf4j
public class IT01_UdpDemo {

    public static void main(String[] args) {

        Runnable sender = () -> {
            try (DatagramSocket ds = new DatagramSocket()) {
                String s = "聊天室开启";
                byte[] bytes = s.getBytes();
                // 使用目的地址 127.0.0.1 是单播
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, 10086);
                Scanner sc = new Scanner(System.in); // InputStream 字节输入流
                while (true) {
                    System.out.println("请输入需要发送的文字，按回车发送:");
                    s = sc.nextLine();
                    dp.setData(s.getBytes());
                    ds.send(dp);
                    if ("886".equals(s)) {
                        break;
                    }
                }
                log.info("退出聊天室");
                ds.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 自动释放资源
            // ds.close()

        };

        new Thread(new UdpReceiver(10086), "接收端").start();
        new Thread(sender, "发送端").start();
    }

}
