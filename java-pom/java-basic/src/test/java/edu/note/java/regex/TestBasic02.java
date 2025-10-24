package edu.note.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestBasic02 {

    @Test
    void test01() {

        /*需求:
            将字符串:我要学学编编编编程程程程程程替换为:我要学编程
        */
        String str = "我要学学编编编编程程程程程程";

        // 需求:把重复的内容 替换为 单个的
        // 学学                学
        // 编编编编            编
        // 程程程程程程        程
        //  (.)表示把重复内容的第一个字符看做一组
        //  \\1表示第一字符再次出现
        //  + 至少一次
        //  $1 表示把正则表达式中第一组的内容，再拿出来用
        String result = str.replaceAll("(.)\\1+", "$1");
        System.out.println(result);


    }

    @Test
    @DisplayName("贪婪匹配")
    void test02() {

        // 需求1:判断一个字符串的开始字符和结束字符是否一致?只考虑一个字符
        // \\组号:表示把第X组的内容再出来用一次
        String regex1 = "(.).+\\1";
        System.out.println("a123a".matches(regex1));

        // 需求2:判断一个字符串的开始部分和结束部分是否一致?可以有多个字符
        // 举例: abc123abc b456b 123789123 &!@abc&!@ abc123abd(false)
        String regex2 = "(.+).+\\1";
        System.out.println("abc123abc".matches(regex2));

        // 需求3:判断一个字符串的开始部分和结束部分是否一致?开始部分内部每个字符也需要一致
        // 举例: aaa123aaa bbb456bbb 111789111 &&abc&&
        //(.):把首字母看做一组
        // \\2:把首字母拿出来再次使用
        // *:作用于\\2,表示后面重复的内容出现日次或多次
        String regex3 = "((.)\\2*).+\\1";
        System.out.println("aaa123aaa".matches(regex3));
        System.out.println("aaa123aab".matches(regex3));
    }

    @Test
    void test03() {

        // public string replaceAll(string regex,string newstr) 按照正则表达式的规则进行替换
        // public string[] split(string regex): 按照正则表达式的规则切割字符串

        /*
            有一段字符串:小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠
            要求1:把字符串中三个姓名之间的字母替换为vs
            要求2:把字符串中的三个姓名切割出来*/

        String s = "小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠";
        // 细节:
        // 方法在底层跟之前一样也会创建文本解析器的对象
        // 然后从头开始去读取字符串中的内容，只要有满足的，那么就用第一个参数去替换。
        String result1 = s.replaceAll("[\\w&&[^_]]+", "vs");
        System.out.println(result1);

        String[] arr = s.split("[\\w&&[^_]]+");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    @Test
    void test04() {

        /*
            只写+和*表示贪婪匹配

            +? 非贪婪匹配
            *? 非贪婪匹配

            贪婪爬取:在爬取数据的时候尽可能的多获取数据
            非贪婪爬取:在爬取数据的时候尽可能的少获取数据

            ab+:
            贪婪爬取:abbbbbbbbbbbb
            非贪婪爬取:ab
        */
        String s = "Java自从95年问世以来，abbbbbbbbbbbbaaaaaaaaaaaaaaaaaa" +
            "经历了很多版木，目前企业中用的最多的是]ava8和]ava11，因为这两个是长期支持版木。" +
            "下一个长期支持版本是Java17，相信在未来不久Java17也会逐渐登上历史舞台";

        String regex = "ab+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println(m.group());
        }

    }
}
