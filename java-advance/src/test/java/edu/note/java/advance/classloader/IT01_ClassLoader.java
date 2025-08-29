package edu.note.java.advance.classloader;


import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class IT01_ClassLoader {

    @Test
    void testClassLoader() {
        // 获取应用程序类加载器
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("应用程序类加载器" + IT01_ClassLoader.class.getClassLoader());
        System.out.println("应用程序类加载器" + appClassLoader);
        // Assertions.assertEquals(ClassLoaders.appClassLoader(), appClassLoader);
        // 获取扩展类加载器
        ClassLoader extendClassLoader = appClassLoader.getParent();
        System.out.println("平台类加载器" + extendClassLoader);
        // Assertions.assertEquals(ClassLoaders.platformClassLoader(), extendClassLoader);
        // 获取启动类加载器
        ClassLoader bootstrapClassLoader = extendClassLoader.getParent();
        System.out.println("启动类加载器" + bootstrapClassLoader);
        assertNull(bootstrapClassLoader);
    }

}
