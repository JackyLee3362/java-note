package edu.note.java.delegate;

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
public class ProxyTest {

    @Test
    @DisplayName("测试代理")
    void test() {

        // 1. 获取代理的对象
        ActionImpl action = new ActionImpl("Alice");
        IAction proxy = ActionProxy.createProxy(action);

        // 2. 调用 register 的方法
        String result = proxy.register("User0001");
        System.out.println(result);

        // 3. 调用 browse 的方法
        proxy.browse();

    }
}
