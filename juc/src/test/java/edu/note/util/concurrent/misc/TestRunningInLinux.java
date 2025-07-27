package edu.note.util.concurrent.misc;

/**
 * T01_TestRunningLinux
 * day01-03.008 在Linux上测试进程
 * java -cp . Test2 启动
 * ps -fe | grep "java"
 * top -H -p [pid] 查看线程
 * jstack [pid] 快照信息
 * jconsole
 * java -Djava.rmi.server.hostname=172.31.132.194 -Dcom.sun.management.jmxremote
 * -Dcom.sun.management.jmxremote.port=12345 -Dcom.sun.management.jmxremote.ssl=false
 * -Dcom.sun.management.jmxremote.authenticate=false Test2
 */
public class TestRunningInLinux {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception ignored) {
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (Exception ignored) {
                }
            }
        }, "t2").start();
    }
}
