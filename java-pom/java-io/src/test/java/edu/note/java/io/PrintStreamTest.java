package edu.note.java.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 上午12:02
 */
/*
 * 打印流
 * 构造方法
 * public PrintStream(OutputStream/File/String) 关联字节输出流/文件/文件路径
 * public PrintStream(String fileName, Charset charset) 指定字符编码
 * public PrintStream(OutputStream out, boolean autoFlush) 自动刷新
 * public PrintStream(OutputStream out, boolean autoFlush, String encoding)
 * 指定字符编码且自动刷新
 * 成员方法：
 * public void write(int b) 常规方法：规则跟之前一样，将指定的字节写出
 * public void println(Xxx xx) 特有方法：打印任意数据，自动刷新，自动换行
 * public void print(Xxx xx) 特有方法：打印任意数据，不换行
 * public void printf(String format, Object... args) 特有方法：带有占位符的打印语句，不换行
 */
public class PrintStreamTest extends BaseIOTest {

    File read = new File(ioDir, "print.read.txt");
    File write = new File(ioDir, "print.write.txt");

    @Test
    @DisplayName("字节打印流 Demo")
    void test01() throws IOException {

        // 1.创建字节打印流的对象
        PrintStream ps = new PrintStream(Files.newOutputStream(write.toPath()));
        // 2.写出数据
        ps.println(97);// 写出 + 自动刷新 + 自动换行
        ps.print(true);
        ps.println();
        ps.printf("%s", "foo");
        // 3.释放资源
        ps.close();
    }

    @Test
    @DisplayName("系统中的标准输出流")
    void test04() {
        /*
         * 打印流的应用场景
         **/
        // 获取打印流的对象，此打印流在虚拟机启动的时候，由虚拟机创建，默认指向控制台
        // 特殊的打印流，系统中的标准输出流,是不能关闭，在系统中是唯一的。
        PrintStream ps = System.out;

        // 调用打印流中的方法println
        // 写出数据，自动换行，自动刷新
        ps.println("123");
        // ps.close();
        System.out.close();
        System.out.println("456");
    }

    @Test
    @DisplayName("printf 样例")
    void test02() {
        PrintStream ps = System.out;

        // % n表示换行
        System.out.printf("字符: %c %n", 'H');
        System.out.printf("字符串: %s %n", "FOO");
        System.out.printf("布尔值: %b %n", true);
        System.out.printf("计算的结果转16进制：%a %n", 50 * 0.85);
        System.out.printf("科学计数法：%e %n", 50 * 0.85);
        System.out.printf("计算的结果转成指数和浮点数，结果的长度较短的是：%g %n", 50 * 0.85);

    }

    @Test
    @DisplayName("字符串格式化")
    void testString() {

    }

    @Test
    @DisplayName("整形格式化")
    void testInteger() {
        System.out.printf("10进制整形: %d %n", 100);
        System.out.printf("16进制整形: %x %n", 100);
        System.out.printf("8进制整形: %o %n", 100);
        System.out.printf("百分号: %d%% %n", 85);
        System.out.printf("数字补零: %03d %n", 7);
        System.out.printf("数字补零: %03d %n", 70000);
        System.out.printf("数字补空格: % 3d %n", 7);
        System.out.printf("整数分组的效果是: %,d %n", 999998997);
        // TODO 按照 _ 分割
        System.out.printf("整数分组的效果是: % d %n", 999998997);
    }

    @Test
    @DisplayName("浮点型格式化")
    void testFloat() {
        // 默认是 .6f 控制小数长度
        // 1. 小数超过会四舍五入
        // 2. 整数部分按实际为准
        System.out.printf("浮点数默认格式 %f %n", 50.05);
        System.out.printf("浮点数1小数 %.1f %n", 50.05);
        System.out.printf("浮点数2小数 %.2f %n", 50.05);
        System.out.printf("浮点数3小数 %.3f %n", 50.05);
        System.out.printf("浮点数2整数 %1f %n", 50.05);
        System.out.printf("浮点数2整数 %2f %n", 50.05);
        System.out.printf("浮点数3整数 %3f %n", 50.05);
        System.out.printf("浮点数4整数 %4f %n", 50.05);

        System.out.printf("浮点数2整数5小数 %2.5f %n", 49.8);
        System.out.printf("浮点数括号 %(f %n", 50.05);
        System.out.printf("浮点数括号 %(f %n", -70.07);

        // <，表示采取跟前面一样的内容
        System.out.printf("%f和%3.2f %n", 80.08, 3.1415926);
        System.out.printf("%f和%<3.2f %n", 80.08);
    }

    @Test
    @DisplayName("浮点数格式化 %g")
    void testFloat2() {
        // 控制总长度
        System.out.printf("浮点数: %.4g %n", 50.05);
        System.out.printf("浮点数: %.5g %n", 50.05);
        System.out.printf("浮点数: %.6g %n", 50.05);

    }

    @Test
    @DisplayName("日期格式化")
    void testDate() {

        Date date = new Date();
        // %t 表示时间，但是不能单独出现，要指定时间的格式
        // %tc 周二 12月 06 22:08:40 CST 2022
        // %tD 斜线隔开
        // %tF 冒号隔开（12小时制）
        // %tr 冒号隔开（24小时制）
        // %tT 冒号隔开（24小时制，带时分秒）
        System.out.printf("全部日期和时间信息：%tc %n", date);
        System.out.printf("月/日/年格式：%tD %n", date);
        System.out.printf("年-月-日格式：%tF %n", date);
        System.out.printf("HH:MM:SS PM格式(12时制)：%tr %n", date);
        System.out.printf("HH:MM格式(24时制)：%tR %n", date);
        System.out.printf("HH:MM:SS格式(24时制)：%tT %n", date);

        System.out.printf("星期的简称：%ta %n", date);
        System.out.printf("星期的全称：%tA %n", date);
        System.out.printf("英文月份简称：%tb %n", date);
        System.out.printf("英文月份全称：%tB %n", date);
        System.out.printf("年的前两位数字(不足两位前面补0)：%tC %n", date);
        System.out.printf("年的后两位数字(不足两位前面补0)：%ty %n", date);
        System.out.printf("一年中的第几天：%tj %n", date);
        System.out.printf("两位数字的月份(不足两位前面补0)：%tm %n", date);
        System.out.printf("两位数字的日(不足两位前面补0)：%td %n", date);
        System.out.printf("月份的日(前面不补0)：%te  %n", date);

        System.out.printf("两位数字24时制的小时(不足2位前面补0):%tH %n", date);
        System.out.printf("两位数字12时制的小时(不足2位前面补0):%tI %n", date);
        System.out.printf("两位数字24时制的小时(前面不补0):%tk %n", date);
        System.out.printf("两位数字12时制的小时(前面不补0):%tl %n", date);
        System.out.printf("两位数字的分钟(不足2位前面补0):%tM %n", date);
        System.out.printf("两位数字的秒(不足2位前面补0):%tS %n", date);
        System.out.printf("三位数字的毫秒(不足3位前面补0):%tL %n", date);
        System.out.printf("九位数字的毫秒数(不足9位前面补0):%tN %n", date);
        System.out.printf("小写字母的上午或下午标记(英)：%tp %n", date);
        System.out.printf("小写字母的上午或下午标记(中)：%tp %n", date);
        System.out.printf("相对于GMT的偏移量:%tz %n", date);
        System.out.printf("时区缩写字符串:%tZ%n", date);
        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts %n", date);
        System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ %n", date);
    }

    @Test
    @DisplayName("字符打印流 Demo")
    void test03() throws IOException {
        /*
         * 构造方法
         * public PrintWriter(Write/File/String) 关联字节输出流/文件/文件路径
         * public PrintWriter(String fileName, Charset charset) 指定字符编码
         * public PrintWriter(Write, boolean autoFlush) 自动刷新
         * public PrintWriter(OutputStream out, boolean autoFlush)
         * public PrintWriter(Write out, boolean autoFlush, String encoding) 指定字符编码且自动刷新
         * 成员方法：
         * public void write(int b) 常规方法：规则跟之前一样，将指定的字节写出
         * public void println(Xxx xx) 特有方法：打印任意数据，自动刷新，自动换行
         * public void print(Xxx xx) 特有方法：打印任意数据，不换行
         * public void printf(String format, Object... args) 特有方法：带有占位符的打印语句，不换行
         */

        // 1.创建字符打印流的对象
        PrintWriter pw = new PrintWriter(new FileWriter(write), true);

        // 2.写出数据
        pw.println("Foo");
        pw.print("Bar");
        // 3.释放资源
        pw.close();

    }

}
