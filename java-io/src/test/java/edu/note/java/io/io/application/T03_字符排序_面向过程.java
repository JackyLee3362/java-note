package edu.note.java.io.io.application;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class T03_字符排序_面向过程 {

    @Test
    @DisplayName("字符排序_面向过程")
    public void test03() throws IOException {
        /*
            文本文件中有以下的数据：
                2-1-9-4-7-8
            将文件中的数据进行排序，变成以下的数据：
                1-2-4-7-8-9
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
}