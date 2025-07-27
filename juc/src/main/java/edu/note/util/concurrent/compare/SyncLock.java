package edu.note.util.concurrent.compare;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/7/26 22:01
 */
@Slf4j
class SyncLock extends ReentrantLock {

    Condition waitSet = this.newCondition();
    private int flag;
    private final int loopNumber;

    public SyncLock(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(int waitFlag, int nextFlag, String str) {
        for (int i = 0; i < loopNumber; i++) {
            this.lock();
            try {
                while (this.flag != waitFlag) {
                    try {
                        waitSet.await();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
                System.out.print(str);
                this.flag = nextFlag;
                waitSet.signalAll();
            } finally {
                this.unlock();
            }
        }
    }
}
