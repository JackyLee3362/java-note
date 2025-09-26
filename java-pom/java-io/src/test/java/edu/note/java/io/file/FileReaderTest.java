package edu.note.java.io.file;

import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/9/26 12:18
 */
public class FileReaderTest {
    @Test
    public void test1() throws IOException, IOException {
        // 读取文件是在当前模块目录
        FileReader fileReader = new FileReader("pom.xml");
        int data;
        while ((data = fileReader.read()) != -1) {
            System.out.print((char) data);
        }
        fileReader.close();
    }

}
