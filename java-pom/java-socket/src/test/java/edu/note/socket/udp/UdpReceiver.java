package edu.note.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author JackyLee
 * @date 2023-12-14 02:06
 */
public class UdpReceiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10086);
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        String s = null;
        System.out.println("聊天室开启");
        while (true) {
            ds.receive(dp);
            byte[] data = dp.getData();
            int len = dp.getLength();
            s = new String(data, 0, len);
            System.out.println(s);
            if ("886".equals(s)) {
                break;
            }
        }
        System.out.println("聊天室关闭");
        ds.close();
    }
}
