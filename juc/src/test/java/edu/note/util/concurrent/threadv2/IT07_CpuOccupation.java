package edu.note.util.concurrent.threadv2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/12/2 17:02
 */
@Slf4j
public class IT07_CpuOccupation {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }).start();
    }
}
