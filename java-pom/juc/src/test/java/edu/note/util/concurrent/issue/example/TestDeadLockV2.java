package edu.note.util.concurrent.issue.example;

import edu.note.util.concurrent.model.Chopstick;
import edu.note.util.concurrent.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

public class TestDeadLockV2 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new PhilosopherV2("苏格拉底", c1, c2).start();
        new PhilosopherV2("柏拉图", c2, c3).start();
        new PhilosopherV2("亚里士多德", c3, c4).start();
        new PhilosopherV2("赫拉克利特", c4, c5).start();
        new PhilosopherV2("阿基米德", c5, c1).start();
    }
}

@Slf4j(topic = "c.PhilosopherV2")
class PhilosopherV2 extends Thread {
    Chopstick left;
    Chopstick right;

    public PhilosopherV2(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            //　尝试获得左手筷子
            if (left.tryLock()) {
                try {
                    // 尝试获得右手筷子
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() {
        log.debug("eating...");
        Sleeper.sleep(1);
    }
}



