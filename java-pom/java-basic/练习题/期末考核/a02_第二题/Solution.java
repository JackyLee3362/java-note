package 其他.d38_期末考核.a02_第二题;

import java.util.*;

/**
 * ### 第二题（10）
 * 需求
 * 某个班级组织团建活动，班长给出了几个去处给大家选择，分别是 “农家乐” , "轰趴"，“野外拓展”，“健身房”，本次活动每个学生是可以多选的。
 * 现在有如下5名学生选择了如下去处。
 * ```
 * 张全蛋儿:农家乐,野外拓展
 * 李二狗子:轰趴,野外拓展,健身房
 * 翠花:野外拓展
 * 小帅:轰趴,健身房
 * 有容:农家乐
 * ```
 * 具体的功能点如下：
 * 1) 请找出每个去处想去的人数是多少，并输出投票最多的去处是哪个。
 * 2) 请找出哪些人没有选择投票最多的去处，输出他们的名字。（**本案例用不用stream流做都给分**）
 * 比如：小帅，有容没有选择野外拓展。
 */
public class Solution {
    public static void main(String[] args) {
        String text = """
                张全蛋儿:农家乐,野外拓展
                李二狗子:轰趴,野外拓展,健身房
                翠花:野外拓展
                小帅:轰趴,健身房
                有容:农家乐
                """;
        HashMap<String, HashSet<String>> map = new HashMap<>();
        String[] textSplit = text.split("\n");

        // todo: 优化为stream
//        System.out.println("stream方法");
//        Arrays.stream(text.split("\n"))
//                .map(s -> HashMap.)
        System.out.println("函数式方法");
        for (String s : textSplit) {
            String name = s.split(":")[0];
            for (String place : s.split(":")[1].split(",")) {
                if (map.containsKey(place)) {
                    HashSet<String> nameList = map.get(place);
                    nameList.add(name);
                } else {
                    HashSet<String> nameList = new HashSet<>();
                    nameList.add(name);
                    map.put(place, nameList);
                }
            }
        }
        map.keySet().forEach(s -> {
            System.out.println(s + ":" + map.get(s).size() + "人，分别是" + map.get(s));
        });

        String maxPlace = map.keySet().stream().max(Comparator.comparingInt(s -> map.get(s).size())).get();
        HashSet<String> maxNameList = map.get(maxPlace);
        String res = map.values().stream()
                .flatMap(HashSet::stream)// .flatMap(set->set.stream())
                .distinct()
                .filter(s -> !maxNameList.contains(s))
                .reduce((a, b) -> a + "," + b).get() + "没有选择野外扩展";
        System.out.println(res);


    }
}
