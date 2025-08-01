package edu.note.java.io.file;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 统计文件夹大小
public class IT04_FileStat extends BaseIOTest {

    @Test
    @DisplayName("统计一个文件夹的总大小")
    public void test() {

        File file = new File(resource);

        long len = FileUtil.getLen(file);
        System.out.println(len);
    }

    @Test
    @DisplayName("递归统计一个文件夹中每种文件的个数")
    public void test02() {

        File file = new File(resource);
        Map<String, Integer> hm = FileUtil.getCount(file);
        System.out.println(hm);
    }

}
