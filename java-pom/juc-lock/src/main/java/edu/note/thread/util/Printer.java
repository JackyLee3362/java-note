package edu.note.thread.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025/9/18 09:33
 */
@Slf4j(topic = "c.printer")
public class Printer {

    /**
     * 打印等待线程
     *
     * @param lock
     */
    public static void printWaitingThreads(Object lock) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);

        for (ThreadInfo threadInfo : threadInfos) {
            if (threadInfo != null && threadInfo.getLockName() != null) {
                String lockName = threadInfo.getLockName();
                if (lockName.equals(lock.toString())) {
                    log.debug("Thread {} is waiting on lock {}", threadInfo.getThreadId(), lockName);
                }
            }
        }
    }
}
