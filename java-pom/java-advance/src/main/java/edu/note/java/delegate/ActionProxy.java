package edu.note.java.delegate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类的作用： 创建一个代理
 */
public class ActionProxy {

    /**
     * 作用： 为 Action 对象创建个代理
     * 获取代理并调用代理方法
     *
     * @param action 被代理对象
     * @return 代理
     */
    public static IAction createProxy(IAction action) {
        // 类加载器，加载生成的代理类
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // 指定接口
        Class<?>[] interfaces = new Class[] { IAction.class };
        // (代理对象, 代理方法, 代理方法实参) -> {...}
        InvocationHandler handler = (proxy, method, args) -> {
            System.out.println("[ 代理 ]-----开始-----");
            // 使用反射代理方法 register
            Method singMethod = IAction.class.getDeclaredMethod("register", String.class);
            // 使用反射代理方法 browse
            Method danceMethod = IAction.class.getDeclaredMethod("browse");
            if (method == singMethod) {
                System.out.println("[ 代理 ] 准备注册脚本");
            } else if (method == danceMethod) {
                System.out.println("[ 代理 ] 准备推荐内容");
            }
            Object o = method.invoke(action, args);
            System.out.println("[ 代理 ]-----结束-----");
            return o;
        };
        // java.lang.reflect.Proxy：提供了为对象产生代理的方法：
        return (IAction) Proxy.newProxyInstance(loader, interfaces, handler);

    }
}
