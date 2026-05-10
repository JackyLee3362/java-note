package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-18 08:11
 */
@Slf4j
public class UdpMultiReceiver implements Runnable {

    private String host;
    private int port;

    public UdpMultiReceiver(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try (MulticastSocket ms = new MulticastSocket(port)) {
            InetAddress address = InetAddress.getByName(host);
            ms.joinGroup(address); // 方法被弃用
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            String msg = null;
            // 阻塞
            ms.receive(dp);
            String ip = dp.getAddress().getHostAddress();
            String name = dp.getAddress().getHostName();
            int port = dp.getPort();
            byte[] data = dp.getData();
            int len = dp.getLength();
            msg = new String(data, 0, len);
            log.info("接收到来自 ip={}:{}, name={} 的消息: {}", ip, port, name, msg);

        } catch (IOException e) {
            log.error("发生错误:{}", e.getMessage());
        }
        // 4.释放资源 try-res 自动关闭
        // ds.close();
    }
}
