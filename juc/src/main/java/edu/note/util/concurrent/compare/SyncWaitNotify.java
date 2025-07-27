package edu.note.util.concurrent.compare;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/7/26 22:01
 */
@Slf4j
class SyncWaitNotify {

    private int flag;
    private final int loopNumber;

    public SyncWaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(int waitFlag, int nextFlag, String str) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (this.flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
