package edu.note.socket.p05_多线程上传;

import java.io.*;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Callable;

public class ServerThreadCallable implements Callable {

    private Socket socket;
    public ServerThreadCallable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Object call() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        String s = UUID.randomUUID().toString().replace("-", "");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("mynet\\"+s+".jpg"));
        int len;
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        // 4.回写数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("上传成功");
        bw.newLine();
        bw.flush();
        // 5.释放资源
        socket.close();
        return null;
    }
}
