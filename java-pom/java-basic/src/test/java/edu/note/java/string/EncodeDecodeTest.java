package edu.note.java.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 下午3:06
 */
public class EncodeDecodeTest {

    // public byte[] getBytes() 使用默认方式进行编码
    // public byte[] getBytes(String charsetName) 使用指定方式进行编码
    @Test
    @DisplayName("中文-编码与解码")
    void test01() throws UnsupportedEncodingException {

        // 1.编码
        // java 中的 String默认使用UTF-16编码方式
        // 如果在调试时查看String对象的value(),可以看到是以小端方式（little end存储的）
        String str = "你好世界";
        // 但是在调用getBytes()时是使用UTF-8的编码方式
        byte[] bytes1 = str.getBytes();
        System.out.println(Arrays.toString(bytes1));

        byte[] bytes2 = str.getBytes("GBK");
        System.out.println(Arrays.toString(bytes2));

        byte[] bytes3 = str.getBytes(StandardCharsets.UTF_16);
        System.out.println(Arrays.toString(bytes3));

        // String(byte[] bytes) 使用默认方式进行解码
        // String(byte[] bytes, String charsetName) 使用指定方式进行解码
        String str2 = new String(bytes1);
        System.out.println(str2);

        String str3 = new String(bytes1, "GBK");
        System.out.println(str3);
    }

    @Test
    @DisplayName("emoji字节流and字符流")
    void test02() {

        String e1 = "😊";
        byte[] bytes1 = e1.getBytes();
        char[] chars1 = new char[10];
        e1.getChars(0, e1.length(), chars1, 0);
        System.out.println("字节流二进制表示");
        for (byte b : bytes1) {
            int i = Byte.toUnsignedInt(b); // 将字节转化为整数int
            System.out.print(Integer.toBinaryString(i) + " ");
        }
        System.out.println();
        System.out.println("长度" + e1.length());
        System.out.println("码点长度" + e1.codePoints().count());
        System.out.println("字节流int表示" + Arrays.toString(bytes1));
        System.out.println("字符流1" + Arrays.toString(chars1));
    }

    @Test
    @DisplayName("emoji编码解释")
    void test03() {

        /*
         * Unicode符号范围 | UTF-8编码方式
         * (十六进制) | （二进制）
         * ----------------------+---------------------------------------------
         * 0000 0000-0000 007F | 0xxxxxxx
         * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
         * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
         * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
         */

        // 1.emoji编码
        String e1 = "😊";
        byte[] bytes1 = e1.getBytes();

        /*
         * ! 输出结果
         * chars1[0] '\uD83D' 55357 11011000 00111101
         * chars1[1] '\uDE0A' 56842 11011110 00001010
         * bytes [11110000, 10011111, 10011000, 10001010]
         * ! 解释
         * 😊 在 应该是U+1F60A
         * 0x1F60A - 0x10000 = 0xF60A = 0b 0000 1111 0110 0000 1010
         * 其实是通过代理区域对照表实现的 surrogate pairs
         * 高10bit位 = 0b 0000 1111 01 = 0x3D
         * 低10bit位 = 0b 10 0000 1010 = 0x20A
         * high surrogate = 0xD800 + 0x3D = 0xD83D
         * low surrogate = 0xDC00 + 0x20A = 0xDE0A
         */

        // 2.解码
        String decode = new String(bytes1);
        System.out.println(decode);
        // unicode code point查询网站 https://unicode.scarfboy.com/
        // 转换样本演示 https://blog.csdn.net/cleve_mfr/article/details/128001912
    }

    @Test
    @DisplayName("0xD800的字符")
    void test04() {

        String e1 = "\uD83D\uDE0A"; // 😊
        System.out.println(e1);
        String e2 = "\uD83D"; // ?
        System.out.println(e2);
        String e3 = "\uD801";
        System.out.println(e3); // ?
    }

    @Test
    @DisplayName("char 转 int")
    void test05() {

        char ch = '我';
        System.out.println((int) ch);
        char[] chars = { 'a', '我' };
        int i = Character.codePointAt(chars, 1);
        System.out.println(Character.isHighSurrogate('\uD801'));
        System.out.println(Character.isLowSurrogate('\uDC00'));
        System.out.println(i);

    }
}
