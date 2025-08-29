package edu.note.java.advance.delegate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * 需求：
 * 外面的人想要大明星唱一首歌
 * 1. 获取代理的对象
 * 代理对象 = StarProxy.createProxy(大明星的对象);
 * 2. 再调用代理的唱歌方法
 * 代理对象.唱歌的方法("只因你太美");
 */
public class TestProxy {

    @Test
    @DisplayName("测试代理")
    void test() {

        // 1. 获取代理的对象
        StarImpl bigStar = new StarImpl("鸡哥");
        Star proxy = StarProxy.createProxy(bigStar);

        // 2. 调用唱歌的方法
        String result = proxy.sing("只因你太美");
        System.out.println(result);

        // 3. 调用跳舞的方法
        proxy.dance();

    }
}
