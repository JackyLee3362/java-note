---
title: java 语言级别
tags:
  - 编程
  - java/语言级别
created_time: 2024-02-11
---

## Intro 介绍

### java语言级别分类

1. java8: 新增有lambda语法
2. java11
3. java17
4. java21

### 项目语言级别

### maven项目的语言级别

```xml
<!-- Maven 中的一个配置参数，它指定了编译Java 源代码时使用的版本 -->
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
```

如果我环境中的java是8，但是像上图所示，显示了17，则会编译错误（无效的发行版17）

如果我环境中的java是17，上面也是17，则会编译出17的版本，但是无法给java8的运行，所以需要改为8

### 字节码版本

基本可以认为 jdk版本 + 44 = 字节码版本

| JDK 版本      | 字节码版本    |
|-------------|----------|
| Java 1.0    | 45.0     |
| Java 1.1    | 45.3     |
| Java 1.2    | 46.0     |
| Java 1.3    | 47.0     |
| Java 1.4    | 48.0     |
| Java 5      | 49.0     |
| Java 6      | 50.0     |
| Java 7      | 51.0     |
| **Java 8**  | **52.0** |
| Java 9      | 53.0     |
| Java 10     | 54.0     |
| **Java 11** | **55.0** |
| Java 12     | 56.0     |
| Java 13     | 57.0     |
| Java 14     | 58.0     |
| Java 15     | 59.0     |
| Java 16     | 60.0     |
| **Java 17** | **61.0** |
| Java 18     | 62.0     |

### java的错误顺序：按照调用栈输出

```java
public class Hello {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        test2();
    }

    public static void test2() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 
// java.lang.ArithmeticException: / by zero
//         at Hello.test2(Hello.java:12)
//         at Hello.test1(Hello.java:7)
//         at Hello.main(Hello.java:4)
```

### maven build参数说明

id: 指定唯一标识

phase: 关联的生命周期阶段，比如 `test-compile`

goal/goals: 关联指定的生命周期目标

configuration: 配置

### maven-jar-plugin

[Apache Maven JAR Plugin – Manifest customization](https://maven.apache.org/plugins/maven-jar-plugin/examples/manifest-customization.html)

[Apache Maven Archiver – Reference](https://maven.apache.org/shared/maven-archiver/index.html)

## Ref 参考文献