package 其他.d38_期末考核.a05_第五题;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    Socket socket;
    BufferedWriter bw;
    BufferedReader br;

    @Test
    public void loginTest() throws IOException {
        String[] login = {
                "登录",
                "name=jack&password=123456",};
        String[] chatting = {
                "开始聊天",
                "你在做什么"
        };
        String [] register = {
                "注册",
                "name=ttt&password=1234",
        };
        String [] register2 = {
                "登录",
                "name=ttt&password=12345",
        };
        // 测试注册
        for (String s : register) {
            send(s);
        }
        System.out.println(br.readLine());
        for (String s : register2) {
           send(s);
        }
        System.out.println(br.readLine());
        // 测试登录
        for (String s : login) {
            send(s);
        }
//        Assert.assertEquals("登录问题", "登录成功",br.readLine());
        for (String s : chatting) {
            send(s);
        }
        send("exit");
        socket.close();
    }

    public Client() throws IOException {
        System.out.println("客户端启动");
        socket = new Socket("127.0.0.1", 10086);
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String s) throws IOException {
        bw.write(s);
        bw.newLine();
        bw.flush();
        System.out.println("已发送"+s);
    }
}


