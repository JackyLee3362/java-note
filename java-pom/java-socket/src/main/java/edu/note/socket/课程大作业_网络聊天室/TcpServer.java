package edu.note.socket.课程大作业_网络聊天室;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class TcpServer {

    public static void main(String[] args) throws IOException {
        // 创建服务器
        ServerSocket ss = new ServerSocket(10086);
        Properties props = new Properties();
        props.load(new FileInputStream("mynet/user.prop"));

        while (true) {
            Socket socket = ss.accept();
            Thread t = new Thread(new ServerThread(socket, props));
            t.start();
        }
        // ss.close();
    }
}

class ServerThread implements Runnable {
    static ArrayList<Socket> list = new ArrayList<>();
    Socket socket;
    Properties props;

    public ServerThread(Socket socket, Properties props) throws IOException {
        this.socket = socket;
        this.props = props;
    }

    @Override
    public void run() {
        System.out.println("连接成功，启动线程" + Thread.currentThread().getName());
        try {
            String s;
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 登录 / 注册
            boolean isConnect = true;
            while (isConnect) {
                s = br.readLine(); // 获得选项
                switch (s) {
                    case ("登录"):
                        login(socket);
                        break;
                    case ("注册"):
                        register(socket);
                        break;
                    case ("886"):
                        isConnect = false;
                        break;
                    default:
                        System.out.println("请重新输入");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("线程结束，删除socket" + Thread.currentThread().getName());
        }
    }

    public void login(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s;
        System.out.println("用户登录");
        s = br.readLine();
        System.out.println(s);
        String[] res = parseNamePassword(s);
        String username = res[0];
        String password = res[1];
        if (props.containsKey(username)) {
            String rightPassword = props.getProperty(username);
            if (rightPassword.equals(password)) {
                write2Client("1");
                list.add(socket);
            } else {
                write2Client("2");
                return;
            }
        } else {
            write2Client("3");
            return;
        }
        // 进入聊天室
        System.out.println("进入了聊天室");
        sendToAllUser(username, "进入了聊天室");
        while ((s = br.readLine()) != null) {
            System.out.println(username + "发送了" + s);
            sendToAllUser(username, s);
        }
        // 发送结束消息
        list.remove(socket);
        sendToAllUser(username, "退出了聊天室");
        socket.close();
    }

    public static void register(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("用户注册");
        String s;
        s = br.readLine();
        System.out.println(s);
        String[] res = parseNamePassword(s);
        System.out.println(Arrays.toString(res));
    }

    public void sendToAllUser(String username, String s) throws IOException {
        for (Socket socket1 : list) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
            bw.write(username + "发送了：" + s);
            bw.newLine();
            bw.flush();
        }
    }

    public void write2Client(String message) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(message);
        bw.newLine();
        bw.flush();
    }

    public static String[] parseNamePassword(String s) {
        String[] name;
        String[] password;
        String[] res = { null, null };
        try {
            String[] namePassword = s.split("&");
            name = namePassword[0].split("=");
            password = namePassword[1].split("=");
            res[0] = name[1];
            res[1] = password[1];
        } catch (Exception e) {
            return res;
        }
        return res;
    }
}
