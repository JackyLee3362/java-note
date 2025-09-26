package edu.note.java.io.application;

import edu.note.java.io.BaseIOTest;
import edu.note.java.io.file.FileUtil;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 需求： 定义一个方法找某一个文件夹中，是否有以 md 文件 不需要考虑子文件夹
 */
public class FileSearchByTypeTest extends BaseIOTest {

    File dir = new File(resource, "file");
    String type = "md";

    @Test
    @DisplayName("遍历文件夹找到文件(不考虑子文件夹)")
    void testFindFile() {
        Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .filter(File::isFile)
                .filter(f -> f.getName().endsWith("." + type))
                .forEach(System.out::println);
    }

    @DisplayName("递归遍历找到文件")
    @Test
    public void findFindFileRecursive() {
        // 获取本地所有的盘符
        // File[] roots = File.listRoots();
        List<File> fileByType = FileUtil.findFileByType(dir, type);
    }
}
