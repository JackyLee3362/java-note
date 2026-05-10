package 其他.d38_期末考核.a05_第五题;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    static Socket socket;
    static BufferedWriter bw;

    @Test
    public void run() throws IOException {
        File file = new File("src\\day38_期末考核\\a05_第五题\\users.txt"); // 目录是相对模块的
        BufferedReader brFile = new BufferedReader(new FileReader(file));
        HashMap<String, String> db = new HashMap<>();
        String info = null;
        while ((info = brFile.readLine()) != null) {
            String[] res = info.split("=");
            db.put(res[0], res[1]);
        }
        System.out.println(db);


        System.out.println("服务端启动");
        ServerSocket serverSocket = new ServerSocket(10086);
        socket = serverSocket.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        String s;
        while ((s = br.readLine()) != null && !"exit".equals(s)) {
            System.out.println(s);
            switch (s) {
                case ("登录") -> {
                    s = br.readLine();
                    String[] namePassword = parseNamePassword(s);
                    String name = namePassword[0];
                    String password = namePassword[1];
                    if (!db.containsKey(name)) {
                        send("用户不存在");
                    } else if (!db.get(name).equals(password)) {
                        send("密码错误");
                    } else {
                        send("登录成功");
                        while ((s = br.readLine()) != null && !"exit".equals(s)) {
                            System.out.println(s);
                        }
                    }
                }
                case ("注册") -> {
                    s = br.readLine();
                    String[] namePassword = parseNamePassword(s);
                    String name = namePassword[0];
                    String password = namePassword[1];
                    if (db.containsKey(name)) {
                        send("用户已注册");
                    } else {
                        db.put(name, password);
                        send("注册成功");
                    }
                }
                default -> {
                    send("请重新选择");
                }
            }
        }


    }

    public static String[] parseNamePassword(String s) {
        String name = s.split("&")[0].split("=")[1];
        String password = s.split("&")[1].split("=")[1];
        return new String[]{name, password};
    }

    public static void send(String s) throws IOException {
        bw.write(s);
        bw.newLine();
        bw.flush();
    }

}
