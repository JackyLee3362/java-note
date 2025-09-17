package edu.note.java.re;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestBasic04 {

    @Test
    @Disabled("分组_先行断言_后行断言")
    void test() {

        /*
            需求1:爬取版本号为8，11.17的Java文本，但是只要Java，不显示版本号。
            需求2:爬取版本号为8，11，17的Java文本。正确爬取结果为:Java8 Java11 Java17 Java17
            需求3:爬取除了版本号为8，11.17的Java文本，
        */
        String s = "Java自从95年问世以来，经历了很多版本，目前企业中用的最多的是Java8和Java11，" +
            "因为这两个是长期支持版本，下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        // 1.定义正则表达式
        // ?理解为前面的数据Java
        // =表示在Java后面要跟随的数据
        // 但是在获取的时候，只获取前半部分
        // 需求1:
        String regex1 = "((?i)Java)(?=8|11|17)";
        // 需求2:
        String regex2 = "((?i)Java)(8|11|17)";
        String regex3 = "((?i)Java)(?:8|11|17)";
        // 需求3:
        String regex4 = "((?i)Java)(?!8|11|17)";

        Pattern p = Pattern.compile(regex3);
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}

/* 笔记补充：
https://www.runoob.com/w3cnote/reg-lookahead-lookbehind.html

正则表达式的先行断言(lookahead)和后行断言(lookbehind)
*/
