package edu.note.socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试字节流/字符流/缓冲流的接收和发送
 * @author jackylee
 * @date 2025-10-20 22:03
 */
@Slf4j
public class TcpDemo01Stream {
    @Test
    @DisplayName("实验1: 用 InputStream 接收")
    void test01() throws InterruptedException {
        // 测试接收OutputStream
        // 测试接收OutStreamWriter
        // 测试接收BufferedReader
        Thread t1 = new Thread(receiver1, "receiver");
        Thread t2 = new Thread(sender, "sender");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    @DisplayName("实验1: 用 InputStreamReader 接收")
    void test02() throws InterruptedException {
        // 测试接收OutputStream
        // 测试接收OutStreamWriter
        // 测试接收BufferedReader
        Thread t1 = new Thread(receiver2, "receiver");
        Thread t2 = new Thread(sender, "sender");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    @DisplayName("实验1: 用 BufferReader 接收")
    void test03() throws InterruptedException {
        // 测试接收OutputStream
        // 测试接收OutStreamWriter
        // 测试接收BufferedReader
        Thread t1 = new Thread(receiver3, "receiver");
        Thread t2 = new Thread(sender, "sender");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    Runnable receiver1 = () -> {
        log.info("receiver 启动");
        try (ServerSocket ss = new ServerSocket(10086);
                Socket socket = ss.accept();
                InputStream is = socket.getInputStream();) {

            // 定义接收变量
            int b;

            while ((b = is.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (Exception e) {
            log.error("错误: {}", e);
        }

    };
    Runnable receiver2 = () -> {
        log.info("receiver 启动");
        int b;
        try (ServerSocket ss = new ServerSocket(10086);
                Socket socket = ss.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));) {

            while ((b = isr.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (Exception e) {
            log.error("错误: {}", e);
        }
    };
    Runnable receiver3 = () -> {
        log.info("receiver 启动");
        String s;
        try (ServerSocket ss = new ServerSocket(10086);
                Socket socket = ss.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);) {

            while ((s = br.readLine()) != null) {
                log.info(s);
            }
        } catch (Exception e) {
            log.error("错误: {}", e);
        }
    };

    Runnable sender = () -> {
        String s = "中文English😊";
        try (Socket socket = new Socket("127.0.0.1", 10086);
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);) {

            // 定义变量

            log.info("实验1: 用OutputStream发送");
            os.write(s.getBytes());

            // 发送给os时需要加
            log.info("实验2: 用OutputStreamWriter发送");
            osw.write(s);
            osw.flush();

            log.info("实验3: 用BufferedWriter发送");
            bw.write(s);
            bw.flush();
            socket.shutdownOutput();
        } catch (Exception e) {
            log.error("错误: {}", e);
        }
    };

}
