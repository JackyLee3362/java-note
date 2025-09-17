package edu.note.util.concurrent.threadpool;

// 查看虚拟机可用CPU数量
public class IT03CheckNoCPU {
    public static void main(String[] args) {
        // 向Java虚拟机返回可用处理器的数目
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);
    }
}
