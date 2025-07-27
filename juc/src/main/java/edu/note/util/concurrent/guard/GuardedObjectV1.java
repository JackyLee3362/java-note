package edu.note.util.concurrent.guard;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/7/26 22:06
 */
@Slf4j
public class GuardedObjectV1 {

    private Object response;
    private final Object lock = new Object();

    public Object get() {
        synchronized (lock) {
            // 条件不满足则等待
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }
}
