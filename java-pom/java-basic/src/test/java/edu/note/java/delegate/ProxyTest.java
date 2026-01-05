package edu.note.java.delegate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * 需求：
 * 想要增强 IAction 接口的实现类 ActionImpl 的功能，比如在注册时打印日志，浏览时统计时间等
 * 1. 获取代理的对象
 * 代理对象 = ActionProxy.createProxy(action);
 * 2. 再调用接口的方法
 * 代理对象.代理方法(...);
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
