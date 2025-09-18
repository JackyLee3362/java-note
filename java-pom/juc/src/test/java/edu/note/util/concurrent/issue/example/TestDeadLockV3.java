package edu.note.util.concurrent.issue.example;

import edu.note.util.concurrent.model.Chopstick;
import edu.note.thread.util.Sleeper;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestDeadLockV1")
public class TestDeadLockV3 {
    public static AtomicInteger STATE = new AtomicInteger(0b00000);

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new PhilosopherV3("苏格拉底", c1, c2).start();
        new PhilosopherV3("柏拉图", c2, c3).start();
        new PhilosopherV3("亚里士多德", c3, c4).start();
        new PhilosopherV3("赫拉克利特", c4, c5).start();
        new PhilosopherV3("阿基米德", c5, c1).start();
    }
}

@Slf4j(topic = "c.PhilosopherV3")
class PhilosopherV3 extends Thread {
    Chopstick left;
    Chopstick right;

    public PhilosopherV3(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            do {

            } while (!TestDeadLockV3.STATE.compareAndSet(TestDeadLockV3.STATE.get(), 0b00011));
            //　尝试获得左手筷子
            synchronized (left) {
                // 尝试获得右手筷子
                synchronized (right) {
                    eat();
                }
            }
        }
    }

    private void eat() {
        log.debug("eating...{}+{}", left, right);
        Sleeper.sleep(1);
    }
}

