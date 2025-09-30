package edu.note.java.regex;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestBasic03 {
    @Test
    @Disabled("非捕获分组")
    void test() {

        /*

        非捕获分组:分组之后不需要再用本组数据，仅仅是把数据括起来。

        身份证号码:
        41080119930228457x
        510801197609022309
        15040119810705387X
        130133197204039024
        430102197606046442
        regex represents regular expression
                */
        //身份证号码的简易正则表达式
        //非捕获分组:仅仅是把数据括起来//特点:不占用组号
        //这里\\1报错原因:(?:)就是非捕获分组，此时是不占用组号的。
        // 192.168.1.1


        //(?:) (?=) (?!)都是非捕获分组//更多的使用第一个
        //String regex1 ="[1-9]\\d{16}(?:\\d|x|x)\\1";
        String regex2 ="[1-9]\\d{16}(\\d Xx)\\1";
        //^([01]\d|2[0-3]):[0-5]\d:[@-5]\d$

        System.out.println("41080119930228457x".matches(regex2));
    }
}
