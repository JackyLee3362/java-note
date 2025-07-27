package edu.note.util.concurrent.guard;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 添加多任务处理
 */
@Data
@Slf4j
public class GuardedObjectV3 {

    private final int id;

    private Object response;
    private final Object lock = new Object();


    public GuardedObjectV3(int id) {
        this.id = id;
    }

    public Object get() {
        synchronized (lock) {
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
            this.response = response;
            lock.notifyAll();
        }
    }
}
