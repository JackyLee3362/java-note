package edu.note.util.concurrent.synchronize;

import edu.note.util.concurrent.compare.SyncLock;
import edu.note.util.concurrent.compare.SyncPark;
import edu.note.util.concurrent.compare.SyncWaitNotify;
import org.junit.jupiter.api.Test;

public class TestOrder {

    @Test
    void test3() {
        SyncPark syncPark = new SyncPark(3);
        Thread t1 = new Thread(() -> {
            syncPark.print("a");
        });
        Thread t2 = new Thread(() -> {
            syncPark.print("b");
        });
        Thread t3 = new Thread(() -> {
            syncPark.print("c\n");
        });
        syncPark.setThreads(t1, t2, t3);
        syncPark.start();
    }

    @Test
    void test2() {
        SyncLock syncLock = new SyncLock(1, 5);
        new Thread(() -> {
            syncLock.print(1, 2, "a");
        }).start();
        new Thread(() -> {
            syncLock.print(2, 3, "b");
        }).start();
        new Thread(() -> {
            syncLock.print(3, 1, "c\n");
        }).start();
    }

    @Test
    void test1() {
        SyncWaitNotify syncWaitNotify = new SyncWaitNotify(1, 5);
        new Thread(() -> {
            syncWaitNotify.print(1, 2, "a");
        }).start();
        new Thread(() -> {
            syncWaitNotify.print(2, 3, "b");
        }).start();
        new Thread(() -> {
            syncWaitNotify.print(3, 1, "c\n");
        }).start();
    }

}
