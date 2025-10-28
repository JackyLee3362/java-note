---
type: basic-note
title: java-log
author: JackyLee
create_date: 2025-09-03
update_date:
tags:
description:
---

## logback 加载过程

应用启动，logback 会按照如下顺序进行扫描：

- 在系统配置文件 System Properties 中寻找是否有 logback.configurationFile 对应的 value
- 在 classpath 下寻找是否有 logback.groovy（即 logback 支持 groovy 与 xml 两种配置方式）
- 在 classpath 下寻找是否有 logback-test.xml
- 在 classpath 下寻找是否有 logback.xml

以上任何一项找到了，就不进行后续扫描，按照对应的配置进行 logback 的初始化，具体代码实现可见 ch.qos.logback.classic.util.ContextInitializer 类的 findURLOfDefaultConfigurationFile 方法。

当所有以上四项都找不到的情况下，logback 会调用 ch.qos.logback.classic.BasicConfigurator 的 configure 方法，构造一个 ConsoleAppender 用于向控制台输出日志，默认日志输出格式为"%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"。

- [Java 日志框架：logback 详解 - 五月的仓颉 - 博客园](https://www.cnblogs.com/xrq730/p/8628945.html)

## 日志

日志接口

- ✨ SLF4J: Simple Logging Facade for Java
- Apache.common.Logging(不用)

日志实现

- logback 实现 SLF4J
- log4j2 实现 SLF4J

## 参考资料

- [万字长文带你了解 Java 日志框架使用 Java 日志框架 - 程序员晓凡 - 博客园](https://www.cnblogs.com/xiezhr/p/18358066)
- [Java 日志框架的依赖设置备查(SLF4J, Log4j, Logback) - 后厂村思维导图馆 - 博客园](https://www.cnblogs.com/morvenhuang/p/17658961.html)
- [Chapter 3: Configuration](https://logback.qos.ch/manual/configuration.html)
- [第三章：logback 的配置 | logback](https://logbackcn.gitbook.io/logback/03-di-san-zhang-logback-de-pei-zhi)
- [Logback 指南](https://jishu.dev/2022/05/03/logback/)
- [Java 日志框架解析：汇总及最佳实践-阿里云开发者社区](https://developer.aliyun.com/article/768396)
- [深入掌握 Java 日志体系，再也不迷路了对于一个应用程序来说日志记录是必不可少的一部分。线上问题追踪，基于日志的业务逻辑统 - 掘金](https://juejin.cn/post/6905026199722917902)
