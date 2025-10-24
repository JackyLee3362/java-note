package edu.note.junit.junit5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class IT02BeforeAfter {
    /*
     * 测试原则:测试代码不能污染原数据
     * 
     * 所以通常在Before进行数据初始化
     * 利用Test测试数据
     * After去还原数据
     * 
     * 文件的名字是相对模块的（主类中是相对项目的）
     */

    String srcName = "a.txt";
    String dstName = "b.txt";

    @BeforeEach
    public void backupFile() throws IOException {
        File src = new File(srcName);
        File dest = new File(dstName);
        FileReader fr = new FileReader(src);
        FileWriter fw = new FileWriter(dest);
        int b;
        while ((b = fr.read()) != -1) {
            fw.write(b);
        }
        fr.close();
        fw.close();
    }

    @Disabled
    @Test
    void testMethod() {
        File src = new File(srcName);
        boolean delete = src.delete();
        boolean isExist = src.exists();
        assertTrue(delete);
        assertFalse(isExist);
    }

    @AfterEach
    // 恢复数据
    public void recoverFile() throws IOException {
        File src = new File(srcName);
        File dest = new File(dstName);
        FileReader fr = new FileReader(dest);
        FileWriter fw = new FileWriter(src);
        int b;
        while ((b = fr.read()) != -1) {
            fw.write(b);
        }
        fr.close();
        fw.close();
        dest.delete();
    }
}
