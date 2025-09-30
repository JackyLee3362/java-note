package edu.note.java.delegate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类的作用： 创建一个代理
 */
public class UserProxy {

    /**
     * 作用： 为 User 对象创建个代理
     * 获取代理并调用代理方法
     *
     * @param user 被代理对象
     * @return 代理
     */
    public static User createProxy(User user) {
        // 类加载器，加载生成的代理类
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // 指定接口
        Class<?>[] interfaces = new Class[] { User.class };
        InvocationHandler handler = (proxy/* 代理对象 */, method/* 代理方法 */, args/* 代理方法实参 */) -> {
            System.out.println("[ 代理 ]-----开始-----");
            Method singMethod = User.class.getDeclaredMethod("registe", String.class);
            Method danceMethod = User.class.getDeclaredMethod("browse");
            if (method == singMethod) {
                System.out.println("[ 代理 ] 准备注册脚本");
            } else if (method == danceMethod) {
                System.out.println("[ 代理 ] 准备推荐内容");
            }
            Object o = method.invoke(user, args);
            System.out.println("[ 代理 ]-----结束-----");
            return o;
        };
        // java.lang.reflect.Proxy：提供了为对象产生代理的方法：
        return (User) Proxy.newProxyInstance(loader, interfaces, handler);

    }
}
