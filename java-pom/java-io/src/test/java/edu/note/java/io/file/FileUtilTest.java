package edu.note.java.io.file;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 统计文件夹大小
public class FileUtilTest extends BaseIOTest {

    @Test
    @DisplayName("统计一个文件夹的总大小")
    void test01() {
        File file = new File(resource);

        long len = FileUtil.getLen(file);
        System.out.println(len);
    }

    @Test
    @DisplayName("递归统计一个文件夹中每种文件的个数")
    void test02() {
        File file = new File(resource);
        Map<String, Integer> hm = FileUtil.getCount(file);
        System.out.println(hm);
    }

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

    @Test
    @DisplayName("文件加密")
    void testEncryptFile() throws IOException {
        File src = new File(resource, "file/1.txt");
        File dst = new File(resource, "file/1.txt.enc");
        FileUtil.encryptionAndReduction(src, dst);
    }

    @Test
    @DisplayName("递归拷贝文件夹")
    void testCopyDirRecursive() throws IOException {
        // 拷贝一个文件夹，考虑子文件夹

        // 1.创建对象表示数据源
        File src = new File("copy-dir-test/src");
        // 2.创建对象表示目的地
        File dest = new File("copy-dir-test/dest");

        // 3.调用方法开始拷贝
        FileUtil.copyDir(src, dest);
    }
}
