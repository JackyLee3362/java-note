package edu.note.java.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2024/11/30 上午1:07
 */
@Slf4j
public class FileUtil {

    public static List<File> findFileByType(File src, String type) {
        Objects.requireNonNull(src, "Source directory is null");
        ArrayList<File> res = new ArrayList<>();

        // 使用 Deque 实现非递归版本
        Deque<File> stack = new ArrayDeque<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            File currentDir = stack.pop();
            // 获取当前目录下的所有文件和子目录
            File[] files = currentDir.listFiles();
            if (files == null) {
                continue;
            }
            // 遍历文件和子目录
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().endsWith("." + type)) {
                        log.info("[ FILE SEARCH ] find file({})", file);
                        res.add(file);
                    }
                } else {
                    // 将子目录压入栈中
                    stack.push(file);
                }
            }
        }
        return res;
    }

    /*
     * 作用：删除src文件夹
     * 参数：要删除的文件夹
     * */
    public static void recursiveDelete(File path) {
        Objects.requireNonNull(path);
        if (path.isFile()) {
            boolean delete = path.delete();
            log.info("[ PATH DELETE ] File({}) is deleted({}). ", path, delete);
            return;
        }
        // 递归删除文件夹里面所有的内容
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                recursiveDelete(file);
            }
        }
        boolean delete = path.delete();
        log.info("[ PATH DELETE ] Dir({}) is deleted({}). ", path, delete);
    }

    public static long getLen(File src) {
        Objects.requireNonNull(src);
        // 1.定义变量进行累加
        long len = 0;
        // 2.进入src文件夹
        File[] files = src.listFiles();
        // 3.遍历数组
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            // 4.判断
            if (file.isFile()) {
                // 我们就把当前文件的大小累加到len当中
                len += file.length();
            } else {
                // 判断，如果是文件夹就递归
                len += getLen(file);
            }
        }

        return len;


    }

    /*
     * 作用：
     *       统计一个文件夹中每种文件的个数
     * 参数：
     *       要统计的那个文件夹
     * 返回值：
     *       用来统计map集合
     *       键：后缀名 值：次数
     *
     *       a.txt
     *       a.a.txt
     *       aaa（不需要统计的）
     * */


    private static final int MAX_RECURSION_DEPTH = 100; // 设置最大递归深度

    public static Map<String, Integer> getCount(File src) {
        return getCount(src, 0); // 调用带递归深度的重载方法
    }

    private static Map<String, Integer> getCount(File src, int depth) {
        if (depth > MAX_RECURSION_DEPTH) {
            throw new RuntimeException("Recursion depth exceeded");
        }

        // 1.定义集合用来统计
        Map<String, Integer> hm = new ConcurrentHashMap<>();

        // 2.检查src是否为有效的目录
        if (src == null || !src.exists() || !src.isDirectory()) {
            return hm;
        }

        // 3.进入src文件夹
        File[] files = src.listFiles();
        if (files == null) {
            return hm;
        }

        // 4.遍历数组
        for (File file : files) {
            // 5.判断，如果是文件，统计
            if (file.isFile()) {
                String name = file.getName();
                String[] arr = name.split("\\.");
                if (arr.length >= 2) {
                    String endName = arr[arr.length - 1];
                    hm.merge(endName, 1, Integer::sum);
                }
            } else {
                // 6.判断，如果是文件夹，递归
                Map<String, Integer> sonMap = getCount(file, depth + 1);
                // 7.遍历sonMap把里面的值累加到hm当中
                for (Entry<String, Integer> entry : sonMap.entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    hm.merge(key, value, Integer::sum);
                }
            }
        }
        return hm; // 返回一个新的HashMap，保持接口不变
    }

    public static boolean copyFileBuffer(File src, File dst) {
        // 1.创建对象
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            // 2.拷贝
            int len;
            byte[] bytes = new byte[1024 * 1024 * 5];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        } finally {
            // 3.释放资源
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }

        }
        return true;
    }

}
