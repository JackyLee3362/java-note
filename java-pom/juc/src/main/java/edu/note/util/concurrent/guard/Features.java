package edu.note.util.concurrent.guard;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jackylee
 * @date 2025/7/26 22:17
 */
// 和 GuardedObjectV3 配合使用
public class Features {

    private static final ConcurrentHashMap<Integer, GuardedObjectV3> FEATURES = new ConcurrentHashMap<>();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger();

    public static GuardedObjectV3 createFeature() {
        // 为每个 GuardedObjectV1 分配一个 id
        int id = ID_GENERATOR.incrementAndGet();
        GuardedObjectV3 v3 = new GuardedObjectV3(id);
        // 放入公共位置，将来异步响应返回时，根据编号获取
        FEATURES.put(id, v3);
        return v3;
    }

    public static void complete(int id, Object response) {
        // 异步响应完成，根据编号获取并移除
        GuardedObjectV3 v3 = FEATURES.remove(id);
        if (v3 != null) {
            v3.complete(response);
        }
    }
}
