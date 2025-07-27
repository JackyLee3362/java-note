package edu.note.util.concurrent.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 防止cpu占用过度
 * @author: Jacky Lee
 * @date: 2024/3/31 19:35
 */
@Slf4j
public class TestSleepApplicationCpu {
    /**
     * @description: sleep 的应用，可以减少 cpu 的占用
     * @author: Jacky Lee
     * @date: 2024/4/1 1:51
     */
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }).start();
    }
}
