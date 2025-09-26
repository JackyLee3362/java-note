package edu.note.java.io.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 缓冲流_写出师表
public class BufferedReaderApp01Test {

    @Test
    void test01() throws IOException {
        /*
         * 需求：把《出师表》的文章顺序进行恢复到一个新文件中。
         */

        // 1.读取数据
        BufferedReader br = new BufferedReader(new FileReader("myio\\csb.txt"));
        String line;
        TreeMap<Integer, String> tm = new TreeMap<>();
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\.");
            // 0：序号 1 ：内容
            tm.put(Integer.parseInt(arr[0]), line);
        }
        br.close();

        // 2.写出数据
        BufferedWriter bw = new BufferedWriter(new FileWriter("myio\\result2.txt"));
        Set<Map.Entry<Integer, String>> entries = tm.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            String value = entry.getValue();
            bw.write(value);
            bw.newLine();
        }
        bw.close();

    }
    @Test
    @DisplayName("缓冲流_恢复出师表顺序")
    void test02() throws IOException {

            /*
                需求：把《出师表》的文章顺序进行恢复到一个新文件中。
            */


        // 1.读取数据
        BufferedReader br = new BufferedReader(new FileReader("myio\\csb.txt"));
        String line;
        ArrayList<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();

        // 2.排序
        // 排序规则：按照每一行前面的序号进行排列
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 获取o1和o2的序号
                int i1 = Integer.parseInt(o1.split("\\.")[0]);
                int i2 = Integer.parseInt(o2.split("\\.")[0]);
                return i1 - i2;
            }
        });

        // 3.写出
        BufferedWriter bw = new BufferedWriter(new FileWriter("myio\\result.txt"));
        for (String str : list) {
            bw.write(str);
            bw.newLine();
        }
        bw.close();


    }
}
