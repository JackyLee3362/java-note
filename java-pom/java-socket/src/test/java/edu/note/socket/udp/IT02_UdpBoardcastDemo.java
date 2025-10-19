package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-18 07:56
 */

@Slf4j
public class IT02_UdpBoardcastDemo {

    public static void main(String[] args) throws InterruptedException {

        Runnable sender = () -> {
            try (DatagramSocket ds = new DatagramSocket()) {
                String s = "聊天室开启";
                byte[] bytes = s.getBytes();
                // 使用目的地址 255.255.255.255 是广播
                // 本机只有一个 ip 地址，无法测试
                InetAddress address = InetAddress.getByName("255.255.255.255");
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

        new Thread(new UdpReceiver(10086), "接收端1").start();
        new Thread(sender, "发送端").start();
    }

}
