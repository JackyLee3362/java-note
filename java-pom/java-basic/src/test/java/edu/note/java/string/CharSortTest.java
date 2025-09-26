package edu.note.java.string;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CharSortTest {

    @Test
    @DisplayName("字符排序 面向过程")
    void test03() throws IOException {
        /*
         * 文本文件中有以下的数据：
         * 2-1-9-4-7-8
         * 将文件中的数据进行排序，变成以下的数据：
         * 1-2-4-7-8-9
         */

        // 1.读取数据
        FileReader fr = new FileReader("myio\\number.txt");
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        fr.close();
        System.out.println(sb);
        // 2.排序
        String str = sb.toString();
        String[] arrStr = str.split("-");// 2-1-9-4-7-8

        ArrayList<Integer> list = new ArrayList<>();
        for (String s : arrStr) {
            int i = Integer.parseInt(s);
            list.add(i);
        }
        Collections.sort(list);
        System.out.println(list);
        // 3.写出
        FileWriter fw = new FileWriter("myio\\a.txt");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                fw.write(list.get(i) + "");
            } else {
                fw.write(list.get(i) + "-");
            }
        }
        fw.close();

    }

    @Test
    @DisplayName("字符排序 面向对象")
    void test0x() throws IOException {

        /*
            文本文件中有以下的数据：
                2-1-9-4-7-8
            将文件中的数据进行排序，变成以下的数据：
                1-2-4-7-8-9
           细节1：
                文件中的数据不要换行
            细节2:
                bom头
        */
        // 1.读取数据
        FileReader fr = new FileReader("myio\\a.txt");
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        fr.close();
        System.out.println(sb);
        // 2.排序
        Integer[] arr = Arrays.stream(sb.toString()
                .split("-"))
            .map(Integer::parseInt)
            .sorted()
            .toArray(Integer[]::new);
        // 3.写出
        FileWriter fw = new FileWriter("myio\\a.txt");
        String s = Arrays.toString(arr).replace(", ", "-");
        String result = s.substring(1, s.length() - 1);
        fw.write(result);
        fw.close();


    }
}