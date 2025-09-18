package edu.note.thread.executor;

/**
 * @author jackylee
 * @date 2025/9/18 15:03
 */
@FunctionalInterface // 拒绝策略
interface RejectPolicy<T> {

    void reject(BlockingQueue<T> queue, T task);
}
