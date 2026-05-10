package edu.note.java.util.thread.executor;

/**
 * 拒绝策略
 *
 * @author jackylee
 * @date 2025/9/18 15:03
 */
@FunctionalInterface
public interface MyRejectPolicy<T> {

    void reject(BlockingQueue<T> queue, T task);
}
