---
type: basic-note
title: logback
author: JackyLee
create_date: 2025-10-31
update_date:
tags:
description:
---

## 架构

- Logger: 决定日志继承体系
- Appender: 决定日志输出目的地
- Layout: 决定日志输出格式

Logger 在 logback-classic
Appender 和 Layout 在 logback-core 中

## 日志级别

TRACE < DEBUG < INFO < WARN < ERROR < OFF

## 使用 log.info 时发生了什么

1. 获取过滤链决策 Get the filter chain decision
2. 应用基本选择规则
3. 创建日志事件 Create a LoggingEvent object

## logback 加载过程

应用启动，logback 会按照如下顺序进行扫描：

- 在系统配置文件 System Properties 中寻找是否有 logback.configurationFile 对应的 value
- 在 classpath 下寻找是否有 logback.groovy（即 logback 支持 groovy 与 xml 两种配置方式）
- 在 classpath 下寻找是否有 logback-test.xml
- 在 classpath 下寻找是否有 logback.xml

以上任何一项找到了，就不进行后续扫描，按照对应的配置进行 logback 的初始化，具体代码实现可见 ch.qos.logback.classic.util.ContextInitializer 类的 findURLOfDefaultConfigurationFile 方法。

当所有以上四项都找不到的情况下，logback 会调用 ch.qos.logback.classic.BasicConfigurator 的 configure 方法，构造一个 ConsoleAppender 用于向控制台输出日志，默认日志输出格式为"%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"。

- [Java 日志框架：logback 详解 - 五月的仓颉 - 博客园](https://www.cnblogs.com/xrq730/p/8628945.html)

## FAQ

### Failed to load class

logback 1.2.x -> logback 1.3.x 时发生错误，解决方法就是 slf4j-api 到 2.x

```log
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
```

### java.lang.UnsupportedClassVersionError

logback 1.5.x 是需要 Java 11 的，不支持 Java8

## 参考资料
