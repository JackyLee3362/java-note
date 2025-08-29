package edu.note.java.time.jdk7;

import java.util.Date;

public class A01_Date日期_创建对象 {
    public static void main(String[] args) {
        /*
            public Date() 创建Date对象，表示当前时间
            public Date(long date) 创建Date对象，表示指定时间
            public void setTime(long time) 设置/修改毫秒值
            public long getTime() 获取时间对象的毫秒值
        */

        //1. 创建对象表示一个时间（空参构造，默认当前时间）
        Date d1 = new Date();
        System.out.println("空参构造：" + d1);

        //2. 创建对象表示一个指定的时间
        Date d2 = new Date(0L); // 传入的参数是long型
        System.out.println("有参构造："+d2);

        //3. setTime 修改时间
        //1000毫秒=1秒
        d2.setTime(1000L);
        System.out.println(d2);

        //4. getTime获取当前时间的毫秒值
        long time = d2.getTime();
        System.out.println(time);
    }
}
