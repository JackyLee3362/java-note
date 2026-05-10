package 其他.d38_期末考核.a03_第三题;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * ### 第三题 （15）
 * <p>
 * **需求**
 * <p>
 * * 黑马程序员教学管理系统的菜单信息如下（图1所示），菜单的详细数据存储在**给的素材文件“系统菜单.txt”中**。
 * <p>
 * ![img1](img1.png)
 * <p>
 * **具体要实现的功能点如下所示**
 * <p>
 * 1、请从**系统菜单.txt**中读取这些菜单信息，将这些菜单信息在控制台展示成图1的样子（必须确保展示的顺序是正确的）
 * <p>
 * 2、将正确的菜单顺序，写出到一个新文件**"系统菜单2.txt"**中保存起来，详细格式如下
 * <p>
 * ![img2](image/img2.png)
 * <p>
 * **评分细则**
 * <p>
 * * 能把数据读取出来：3分
 * * 能展示成控制台的样子：8分
 * * 能写出去：4分。
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File("java-basic-note\\src\\day38_期末考核\\a03_第三题\\系统菜单.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;

        HashMap<String, String> hm1 = new HashMap<>(); // 记录第一级映射，e.g {0001: xxx}
        HashMap<String, String> hm2 = new HashMap<>(); // 记录第二级映射，e.g {00010002: xxx}
        TreeMap<String, TreeSet<String>> tm = new TreeMap<>(); // 记录 关联映射，如{0001:[00010002]}
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            String[] res = line.split("-");
            if (res[0].length() == 4) {
                // 如果是第一级，加入hash和tree
                if (!hm1.containsKey(res[0])) {
                    hm1.put(res[0], res[1]);
                }
                if (!tm.containsKey(res[0])) {
                    tm.put(res[0], new TreeSet<>());
                }
            } else {
                // 如果是第二级，加入tree
                // e.g res[0] 000010002
                // e.g res[1] xxx
                String tmKey = res[0].substring(0, 4); // e.g 0001
                String tmValue = res[0].substring(4); // e.g 0002

                System.out.println(tmKey + ":" + tmValue);
                if(!hm2.containsKey(res[0])){
                    hm2.put(res[0], res[1]);
                }

                if (!tm.containsKey(tmKey)) {
                    TreeSet<String> list = new TreeSet<>();
                    list.add(res[0]);
                    tm.put(tmKey, list);
                } else {
                    tm.get(tmKey).add(res[0]);
                }
            }
        }
        System.out.println(hm1);
        System.out.println(hm2);
        System.out.println(tm);

        // 控制台展示
        for (String s : tm.keySet()) {
            System.out.println(hm1.get(s));
            for (String s1 : tm.get(s)) {
                System.out.println("--"+hm2.get(s1));
            }

        }
    }
}
