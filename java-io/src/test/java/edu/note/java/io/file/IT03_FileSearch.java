package edu.note.java.io.file;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * 需求： 定义一个方法找某一个文件夹中，是否有以avi结尾的电影。 （暂时不需要考虑子文件夹）
 */
public class IT03_FileSearch extends BaseIOTest {

    File dir = new File(resource, "file");
    String type = "md";

    @Test
    @DisplayName("遍历文件夹找到文件")
    public void testFindFile() {
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


