package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-18 07:57
 */
@Slf4j
public class UdpReceiver implements Runnable {
    private int port;

    public UdpReceiver(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        // 1.创建DatagramSocket对象
        // 空参：所有可用的端口中随机一个进行使用，一般创建发送端使用
        // 有参：指定端口号进行绑定，一般创建服务端使用
        try (DatagramSocket ds = new DatagramSocket(port)) {
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            String s = null;
            System.out.println("聊天室开启");
            while (true) {
                // 阻塞
                ds.receive(dp);
                String ip = dp.getAddress().getHostAddress();
                String name = dp.getAddress().getHostName();
                int port = dp.getPort();
                byte[] data = dp.getData();
                int len = dp.getLength();
                s = new String(data, 0, len);
                log.info("接收到来自 ip={}:{}, name={} 的消息: {}", ip, port, name, s);
                if ("886".equals(s)) {
                    break;
                }
            }
            log.info("聊天室关闭");

        } catch (IOException e) {
            log.error("发生错误:{}", e.getMessage());
        }
        // 4.释放资源 try-res 自动关闭
        // ds.close();
    }
}
