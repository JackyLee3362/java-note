package edu.note.util.concurrent.issue.example;

import edu.note.util.concurrent.model.Chopstick;
import edu.note.thread.util.Sleeper;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

public class TestDeadLockV1 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new PhilosopherV1("苏格拉底", c1, c2).start();
        new PhilosopherV1("柏拉图", c2, c3).start();
        new PhilosopherV1("亚里士多德", c3, c4).start();
        new PhilosopherV1("赫拉克利特", c4, c5).start();
        new PhilosopherV1("阿基米德", c1, c5).start();
    }
}

@Slf4j(topic = "c.PhilosopherV1")
class PhilosopherV1 extends Thread {
    Chopstick left;
    Chopstick right;

    public PhilosopherV1(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            //　尝试获得左手筷子
            synchronized (left) {
                // 尝试获得右手筷子
                synchronized (right) {
                    eat();
                }
            }
        }
    }

    Random random = new Random();
    private void eat() {
        log.debug("eating...");
        Sleeper.sleep(0.5);
    }
}