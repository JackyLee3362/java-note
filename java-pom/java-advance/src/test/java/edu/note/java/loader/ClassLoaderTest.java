package edu.note.java.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClassLoaderTest {

    @Test
    @DisplayName("测试类加载器")
    void testClassLoader() {
        // 应用程序类加载器
        // 负责加载当前应用 classpath 下的所有 jar 包和类
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        Assertions.assertEquals(appClassLoader, classLoader);

        // 扩展类加载器 / 平台类加载器(JDK 9 之后)
        // 主要负责加载 %JRE_HOME%/lib/ext 目录下的 jar 包和类
        // 以及被 java.ext.dirs 系统变量所指定的路径下的所有类。
        ClassLoader extendClassLoader = appClassLoader.getParent();

        // 启动类加载器
        // 最顶层的加载类，由 C++实现，通常表示为 null，并且没有父级，
        // 主要用来加载 JDK 内部的核心类库（ %JAVA_HOME%/lib目录下的 rt.jar、resources.jar、charsets.jar等 jar 包和类）
        // 以及被 -Xbootclasspath 参数指定的路径下的所有类。
        ClassLoader bootstrapClassLoader = extendClassLoader.getParent();
        System.out.println("启动类加载器" + bootstrapClassLoader);
        Assertions.assertNull(bootstrapClassLoader);
    }

}
