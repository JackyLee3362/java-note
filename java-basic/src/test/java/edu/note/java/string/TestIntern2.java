package edu.note.java.string;

// 2023.12.12 峰哥提出的实验
public class TestIntern2 {
    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        test6();
    }

    // public static void test1(){
    //     System.out.println("测试1");
    //     String s2 = new String("1") + new String("2");
    //
    //     System.out.println(s2 == "12"); // ==  比较地址值
    //     System.out.println("s2的地址是" + System.identityHashCode(s2));
    //     System.out.println("12的地址是" + System.identityHashCode("12"));
    //
    //     s2.intern(); // "12" 推到常量池
    //
    //     System.out.println(s2 == "12"); // ==  比较地址值
    //     System.out.println("s2的地址是" + System.identityHashCode(s2));
    //     System.out.println("12的地址是" + System.identityHashCode("12"));
    // };
    //
    // public static void test2(){
    //     System.out.println("测试2");
    //     String s1 = "12";
    //     String s2 = new String("1") + new String("2");
    //     System.out.println("s1的地址是" + System.identityHashCode(s1));
    //     System.out.println("s2的地址是" + System.identityHashCode(s2));
    //     // System.out.println("12的地址是" + System.identityHashCode("12"));
    //     System.out.println(s2 == "12");
    //     System.out.println(s1 == s2);
    //
    //     s2.intern(); // "12" 推到常量池
    //
    //     System.out.println("s1的地址是" + System.identityHashCode(s1));
    //     System.out.println("s2的地址是" + System.identityHashCode(s2));
    //     // System.out.println("12的地址是" + System.identityHashCode("12"));
    //
    //     System.out.println(s2 == "12");
    //     System.out.println(s1 == s2);
    //
    // }
    // public static void test3() {
    //     System.out.println("测试3");
    //     String s1 = "12";
    //     String s2 = new String("1") + new String("2");
    //     System.out.println(s1.equals(s2));
    //     System.out.println("12".equals(s2));
    //     System.out.println(s2.equals(s1));
    //     System.out.println(s2.equals("12"));
    //     s2.intern(); // "12" 推到常量池
    //     System.out.println("b是"+b);
    //     System.out.println(s2 == "12");
    //     System.out.println(s1 == s2);
    //     System.out.println(b == s1);
    //     System.out.println(b == "12");
    // }
    // public static void test4(){
    //     System.out.println("测试4");
    //     String s2 = new String("1") + new String("2");
    //     String s1 = "12";
    //     s2.intern(); // "12" 推到常量池
    //     System.out.println(s2 == "12");
    //     System.out.println(s1 == "12");
    //     System.out.println(s1 == s2);
    // }
    // public static void test5(){
    //     System.out.println("测试4");
    //     String s1 = "1";
    //     String s2 = new String("1");
    //     s2.intern(); // "12" 推到常量池
    //     System.out.println(s2 == "1");
    //     System.out.println(s1 == "1");
    //     System.out.println(s1 == s2);
    // }
    public static void test6(){
        String s2 = new String("1") + new String("2");
        // System.out.println(s2 == "12"); // 这行代码注释和不注释，结果不一样
        String b = s2.intern();
        System.out.println(s2 == "12");
        System.out.println(b == "12");
    }
}

// https://blog.csdn.net/weixin_42310154/article/details/122861042
// https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
