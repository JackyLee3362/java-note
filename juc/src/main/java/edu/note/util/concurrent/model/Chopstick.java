package edu.note.util.concurrent.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jackylee
 * @date 2025/7/26 23:18
 */
public class Chopstick extends ReentrantLock {

    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}
