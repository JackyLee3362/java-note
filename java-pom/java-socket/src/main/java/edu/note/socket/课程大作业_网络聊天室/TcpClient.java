package edu.note.socket.课程大作业_网络聊天室;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10086);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner sc = new Scanner(System.in);
        String s = null;
        String received = null;
        boolean isConnect = true;
        while (true) { // 2 表示登录成功
            System.out.println("------------欢迎来到聊天室-------------");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("请输入选项");

            s = sc.nextLine();
            switch (s) {
                case "1":
                    login(socket);
                    break;
                case "2":
                    System.out.println("2");
                default:
                    System.out.println("错误选项");
                    break;
            }
        }

    }

    public static void login(Socket socket) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw.write("登录");
        bw.newLine();
        bw.flush();
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入名字");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        sb.append("name=").append(username).append("&password=").append(password);
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();
        System.out.println("等待服务器响应");
        String message = null;
        message = br.readLine();
        switch (message) {
            case ("1"): {
                System.out.println("登录成功，恭喜加入聊天室");
                Thread listener = new Thread(new ReceiverThread(socket));
                listener.start();
                sendToAll(socket);
                break;
            }
            case ("2"): {
                System.out.println("登录失败：密码有误");
                break;
            }
            case ("3"): {
                System.out.println("登录失败：用户不存在");
                break;
            }
            default:
                System.out.println("服务器错误");
        }

    }

    public static void sendToAll(Socket socket) throws IOException {
        String s = null;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner sc = new Scanner(System.in);
        while (!"886".equals(s)) {
            System.out.println("请输入：");
            s = sc.nextLine();
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        socket.shutdownOutput();
    }
}

class ReceiverThread extends Thread {
    private Socket socket;

    public ReceiverThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 接收消息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String reveived;
            while ((reveived = br.readLine()) != null) {
                System.out.println(reveived);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
