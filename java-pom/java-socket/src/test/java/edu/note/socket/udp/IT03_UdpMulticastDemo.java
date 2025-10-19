package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-18 07:51
 */
@Slf4j
public class IT03_UdpMulticastDemo {

    public static void main(String[] args) {

        Runnable sender = () -> {
            try (MulticastSocket ms = new MulticastSocket()) {
                InetAddress address = InetAddress.getByName("224.0.0.1");
                int port = 10000;
                String msg = "组播消息";
                byte[] bytes = msg.getBytes();

                DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
                ms.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 自动释放资源
            // ds.close()

        };

        new Thread(new UdpMultiReceiver("224.0.0.1", 10000), "接收端1").start();
        new Thread(new UdpMultiReceiver("224.0.0.1", 10000), "接收端2").start();
        new Thread(new UdpMultiReceiver("224.0.0.2", 10000), "接收端3").start();
        new Thread(sender, "发送端").start();
    }

}
