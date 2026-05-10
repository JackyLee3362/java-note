package edu.note.java.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2026-05-10 12:00
 */
@Slf4j
public class IOUtil {

    private static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    private static final String resource = Objects.requireNonNull(classLoader.getResource(".")).getPath();

    // 将 String 转换为 InputStream
    public static InputStream convertStringToInputStream(String content) {
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    // 创建临时文件
    public static File createTempFile(String content) {
        try {
            File tempFile = File.createTempFile("test", ".txt");
            tempFile.deleteOnExit();
            Files.write(tempFile.toPath(), content.getBytes(StandardCharsets.UTF_8));
            return tempFile;
        } catch (Exception e) {
            return null;
        }
    }

    // 创建临时目录
    public static File createTempDirectory(String directoryName) {
        try {
            File tempDir = Files.createTempDirectory(directoryName).toFile();
            tempDir.deleteOnExit();
            return tempDir;
        } catch (Exception e) {
            return null;
        }
    }

    // 创建 resource 下的文件
    public static File createWritableFile(String fileName) {
        return new File(resource, fileName);
    }

    // 打印文件信息
    public static void statFileInfo(File file) throws IOException {
        log.info("文件名: {}", file.getName());
        log.info("路径: {}", file.getPath());
        log.info("是否存在: {}", file.exists());
        log.info("是否是文件: {}", file.isFile());
        log.info("是否是目录: {}", file.isDirectory());
        log.info("是否有可读权限: {}", file.canRead());
        log.info("是否有可写权限: {}", file.canWrite());
        log.info("是否有执行权限: {}", file.canExecute());
        log.info("最后修改时间: {}", file.lastModified());
        /*
         * 关于路径
         * getAbsolutePath 返回 String，不解析 ./.. 和符号链接，不抛异常
         * getCanonicalPath 返回 String，解析 ./.. 和符号链接，抛 IOException
         * getCanonicalFile 返回 File，解析 ./.. 和符号链接，抛 IOException
         * 
         * **由于 mac 中的 /tmp /var 挂载在 /private 中，所以后两者会解析出 /private
         */
        log.info("绝对路径: {}", file.getAbsolutePath());
        log.info("标准路径: {}", file.getCanonicalPath());
        log.info("标准文件: {}", file.getCanonicalFile());

    }

}
