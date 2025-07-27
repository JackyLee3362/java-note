package edu.note.java.io.io;

import edu.note.java.io.BaseIOTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2024/12/1 上午12:02
 */
@DisplayName("打印流")
public class IT06_PrintStream extends BaseIOTest {

    File dir = new File(resource, "io");
    File f6a = new File(dir, "README-06a.md");
    File f6b = new File(dir, "README-06b.md");


    @Test
    @DisplayName("字节打印流 Demo")
    public void test01() throws FileNotFoundException, UnsupportedEncodingException {

    /*
    构造方法
        public PrintStream(OutputStream/File/String)            关联字节输出流/文件/文件路径
        public PrintStream(String fileName, Charset charset)    指定字符编码
        public PrintStream(OutputStream out, boolean autoFlush)  自动刷新
        public PrintStream(OutputStream out, boolean autoFlush, String encoding)    指定字符编码且自动刷新
    成员方法：
        public void write(int b)            常规方法：规则跟之前一样，将指定的字节写出
        public void println(Xxx xx)         特有方法：打印任意数据，自动刷新，自动换行
        public void print(Xxx xx)           特有方法：打印任意数据，不换行
        public void printf(String format, Object... args)   特有方法：带有占位符的打印语句，不换行
    */

        // 1.创建字节打印流的对象
        PrintStream ps = new PrintStream(new FileOutputStream(f6a));
        // 2.写出数据
        ps.println(97);// 写出 + 自动刷新 + 自动换行
        ps.print(true);
        ps.println();
        ps.printf("%s爱上了%s", "阿珍", "阿强");
        // 3.释放资源
        ps.close();
    }

    @Test
    @DisplayName("printf 样例")
    public void test02() throws FileNotFoundException {
        PrintStream ps = System.out;

        //% n表示换行
        ps.printf("我叫%s %n", "阿玮");
        ps.printf("%s喜欢%s %n", "阿珍", "阿强");
        ps.printf("字母H的大写：%c %n", 'H');
        ps.printf("8>3的结果是：%b %n", 8 > 3);
        ps.printf("100的一半是：%d %n", 100 / 2);
        ps.printf("100的16进制数是：%x %n", 100);
        ps.printf("100的8进制数是：%o %n", 100);
        ps.printf("50元的书打8.5折扣是：%f元%n", 50 * 0.85);
        ps.printf("计算的结果转16进制：%a %n", 50 * 0.85);
        ps.printf("计算的结果转科学计数法表示：%e %n", 50 * 0.85);
        ps.printf("计算的结果转成指数和浮点数，结果的长度较短的是：%g %n", 50 * 0.85);
        ps.printf("带有百分号的符号表示法，以百分之85为例：%d%% %n", 85);
        ps.println("---------------------");

        double num1 = 1.0;
        ps.printf("num: %.4g %n", num1);
        ps.printf("num: %.5g %n", num1);
        ps.printf("num: %.6g %n", num1);

        float num2 = 1.0F;
        ps.printf("num: %.4f %n", num2);
        ps.printf("num: %.5f %n", num2);
        ps.printf("num: %.6f %n", num2);
        ps.println("---------------------");

        ps.printf("数字前面带有0的表示方式：%03d %n", 7);
        ps.printf("数字前面带有0的表示方式：%04d %n", 7);
        ps.printf("数字前面带有空格的表示方式：% 8d %n", 7);
        ps.printf("整数分组的效果是：%,d %n", 9989997);
        ps.println("---------------------");

        // 最终结果是10位，小数点后面是5位，不够在前面补空格，补满10位
        // 如果实际数字小数点后面过长，但是只规定两位，会四舍五入
        // 如果整数部分过长，超出规定的总长度，会以实际为准
        ps.printf("一本书的价格是：%2.5f元%n", 49.8);
        ps.printf("%(f%n", -76.04);

        //%f，默认小数点后面7位，
        //<，表示采取跟前面一样的内容
        ps.printf("%f和%3.2f %n", 86.04, 1.789651);
        ps.printf("%f和%<3.2f %n", 86.04, 1.789651);
        ps.println("---------------------");

        Date date = new Date();
        // %t 表示时间，但是不能单独出现，要指定时间的格式
        // %tc 周二 12月 06 22:08:40 CST 2022
        // %tD 斜线隔开
        // %tF 冒号隔开（12小时制）
        // %tr 冒号隔开（24小时制）
        // %tT 冒号隔开（24小时制，带时分秒）
        ps.printf("全部日期和时间信息：%tc %n", date);
        ps.printf("月/日/年格式：%tD %n", date);
        ps.printf("年-月-日格式：%tF %n", date);
        ps.printf("HH:MM:SS PM格式(12时制)：%tr %n", date);
        ps.printf("HH:MM格式(24时制)：%tR %n", date);
        ps.printf("HH:MM:SS格式(24时制)：%tT %n", date);

        ps.printf("星期的简称：%ta %n", date);
        ps.printf("星期的全称：%tA %n", date);
        ps.printf("英文月份简称：%tb %n", date);
        ps.printf("英文月份全称：%tB %n", date);
        ps.printf("年的前两位数字(不足两位前面补0)：%tC %n", date);
        ps.printf("年的后两位数字(不足两位前面补0)：%ty %n", date);
        ps.printf("一年中的第几天：%tj %n", date);
        ps.printf("两位数字的月份(不足两位前面补0)：%tm %n", date);
        ps.printf("两位数字的日(不足两位前面补0)：%td %n", date);
        ps.printf("月份的日(前面不补0)：%te  %n", date);

        ps.printf("两位数字24时制的小时(不足2位前面补0):%tH %n", date);
        ps.printf("两位数字12时制的小时(不足2位前面补0):%tI %n", date);
        ps.printf("两位数字24时制的小时(前面不补0):%tk %n", date);
        ps.printf("两位数字12时制的小时(前面不补0):%tl %n", date);
        ps.printf("两位数字的分钟(不足2位前面补0):%tM %n", date);
        ps.printf("两位数字的秒(不足2位前面补0):%tS %n", date);
        ps.printf("三位数字的毫秒(不足3位前面补0):%tL %n", date);
        ps.printf("九位数字的毫秒数(不足9位前面补0):%tN %n", date);
        ps.printf("小写字母的上午或下午标记(英)：%tp %n", date);
        ps.printf("小写字母的上午或下午标记(中)：%tp %n", date);
        ps.printf("相对于GMT的偏移量:%tz %n", date);
        ps.printf("时区缩写字符串:%tZ%n", date);
        ps.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts %n", date);
        ps.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ %n", date);

        ps.close();
    }

    @Test
    @DisplayName("字符打印流 Demo")
    public void test03() throws IOException {
       /*
        构造方法
            public PrintWriter(Write/File/String)            关联字节输出流/文件/文件路径
            public PrintWriter(String fileName, Charset charset)    指定字符编码
            public PrintWriter(Write, boolean autoFlush)  自动刷新
            public PrintWriter(OutputStream out, boolean autoFlush)
            public PrintWriter(Write out, boolean autoFlush, String encoding)    指定字符编码且自动刷新
        成员方法：
            public void write(int b)            常规方法：规则跟之前一样，将指定的字节写出
            public void println(Xxx xx)         特有方法：打印任意数据，自动刷新，自动换行
            public void print(Xxx xx)           特有方法：打印任意数据，不换行
            public void printf(String format, Object... args)   特有方法：带有占位符的打印语句，不换行
        */

        // 1.创建字符打印流的对象
        PrintWriter pw = new PrintWriter(new FileWriter("myio\\a.txt"), true);

        // 2.写出数据
        pw.println("今天你终于叫我名字了，虽然叫错了，但是没关系，我马上改");
        pw.print("你好你好");
        pw.printf("%s爱上了%s", "阿珍", "阿强");
        // 3.释放资源
        pw.close();


    }

    @Test
    @DisplayName("系统中的标准输出流")
    public void test04() {
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
}
