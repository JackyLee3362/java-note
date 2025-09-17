package edu.note.java.advance.delegate;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类的作用： 创建一个代理
 */
public class StarProxy {

    /**
     * 作用： 给一个明星的对象，创建一个代理 需求 外面的人想要大明星唱一首歌 - 获取代理的对象，代理对象 = {@code StarProxy.createProxy}(大明星的对象) -
     * 再调用代理的唱歌方法，代理对象.唱歌的方法("只因你太美")
     *
     * @param bigStar 被代理的明星对象
     * @return 给明星创建的代理
     */
    public static Star createProxy(Star bigStar) {
        // java.lang.reflect.Proxy类：提供了为对象产生代理对象的方法：
        // 参数三，用来指定生成的代理对象要干什么事情
        return (Star) Proxy.newProxyInstance(
            // 参数一，用于指定用哪个类加载器，去加载生成的代理类
            ClassLoader.getSystemClassLoader(),
            new Class[]{Star.class}, // 参数二，指定接口，这些接口用于指定生成的代理长什么，也就是有哪些方法
            (proxy, method, args) -> {
                /*
                 * 参数一：代理对象
                 * 参数二：代理方法
                 * 参数三：代理方法实参
                 */
                System.out.println("[ 代理 ]-----开始-----");
                Method singMethod = Star.class.getDeclaredMethod("sing", String.class);
                Method danceMethod = Star.class.getDeclaredMethod("dance");
                if (method == singMethod) {
                    System.out.println("[ 代理 ] 准备话筒，收钱");
                } else if (method == danceMethod) {
                    System.out.println("[ 代理 ]准备场地，收钱");
                }
                // 去找大明星开始唱歌或者跳舞
                // 代码的表现形式：调用大明星里面唱歌或者跳舞的方法
                Object o = method.invoke(bigStar, args);
                System.out.println("[ 代理 ]-----结束-----");
                return o;
            });

    }
}
