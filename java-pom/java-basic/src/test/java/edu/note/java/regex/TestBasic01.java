package edu.note.java.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBasic01 {

    @Test
    void test01() {
        String qq = "1234567890";
        boolean matches = qq.matches("[1-9]\\d{5,19}");
        Assertions.assertTrue(matches);
    }

    @Test
    void test02() {
        // public boolean matches(String regex):判断是否与正则表达式匹配，匹配返回true
        // 只能是a b c
        Assertions.assertTrue("a".matches("[abc]")); // true

        // 不能出现a b c
        Assertions.assertTrue("z".matches("[^abc]")); // true

        // a到zA到Z(包括头尾的范围)
        Assertions.assertTrue("0".matches("[a-zA-Z0-9]"));// true

        // [a-d[m-p]] a到d，或m到p
        Assertions.assertTrue("a".matches("[a-d[m-p]]"));// true

        // [a-z&&[def]] a-z和def的交集。为:d，e，f
        Assertions.assertTrue("a".matches("[a-z&[def]]")); // true
        Assertions.assertFalse("d".matches("a-z&&[def]")); // false

        // [a-z&&[^bc]] a-z和非bc的交集。(等同于[ad-z])
        Assertions.assertFalse("a".matches("a-z&&[^bc]"));// true
        Assertions.assertFalse("b".matches("a-z&&[^bc]")); // false
        Assertions.assertFalse("0".matches("a-z&&[^bc]")); // false

        // [a-z&&[^m-p]] a到z和除了m到p的交集。(等同于[a-1q-z])
        Assertions.assertFalse("a".matches("a-z&&[^m-p]")); // true
        Assertions.assertFalse("m".matches("a-z&&[^m-p]")); // false
        Assertions.assertFalse("0".matches("a-z&&[^m-p]")); // false
    }

    @Test
    void test03() {

        // \ 转义字符 改变后面那个字符原本的含义
        // 此时\表示转义字符，改变了后面那个双引号原本的含义

        // .表示任意一个字符
        Assertions.assertTrue("你".matches(".")); // true
        Assertions.assertTrue("你".matches("..")); // false

        // \\d 表示任意的一位数字
        Assertions.assertTrue("3".matches("\\d")); // true

        // \\w只能是一位单词字符[a-zA-Z_0-9]
        Assertions.assertTrue("2".matches("\\w")); // true

        // 非单词字符
        Assertions.assertTrue("你".matches("\\W")); // true
        // 以上正则匹配只能校验单个字符。

        // 必须是数字 字母 下划线 至少 6位
        Assertions.assertTrue("2442fsfsf".matches("\\w{6,}"));// true

        // 必须是数字和字符 必须是4位
        Assertions.assertTrue("23dF".matches("[a-zA-Z0-9]{4}"));// true
        Assertions.assertTrue("23 F".matches("[a-zA-Z0-9]{4}"));// false
        Assertions.assertTrue("23dF".matches("[\\w&&[^_]]{4}"));// true
        Assertions.assertTrue("23_F".matches("[\\w&&[^_]]{4}"));// false

    }
}