package edu.note.java.juc.executor.example;

/**
 * @author jackylee
 * @date 2026-05-10 17:28
 */
@FunctionalInterface
public interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
