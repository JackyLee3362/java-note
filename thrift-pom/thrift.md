---
type: basic-note
title: thrift
author: JackyLee
create_date: 2025-10-11
update_date:
tags:
description:
---

## 快速入门

在 thrift-sdk-compile 中，使用 thrift 命令行运行

```sh
thrift -r -gen java HelloWorldService.thrift
```

## 基本概念

### 1.数据类型

基本类型：
bool：布尔值，true 或 false，对应 Java 的 boolean
byte：8 位有符号整数，对应 Java 的 byte
i16：16 位有符号整数，对应 Java 的 short
i32：32 位有符号整数，对应 Java 的 int
i64：64 位有符号整数，对应 Java 的 long
double：64 位浮点数，对应 Java 的 double
string：utf-8 编码的字符串，对应 Java 的 String
结构体类型：
struct：定义公共的对象，类似于 C 语言中的结构体定义，在 Java 中是一个 JavaBean
容器类型：
list：对应 Java 的 ArrayList
set：对应 Java 的 HashSet
map：对应 Java 的 HashMap
异常类型：
exception：对应 Java 的 Exception
服务类型：
service：对应服务的类

### 2.服务端编码基本步骤：

实现服务处理接口 impl
创建 TProcessor
创建 TServerTransport
创建 TProtocol
创建 TServer
启动 Server 3.客户端编码基本步骤：

创建 Transport
创建 TProtocol
基于 TTransport 和 TProtocol 创建 Client
调用 Client 的相应方法 4.数据传输协议

TBinaryProtocol : 二进制格式.
TCompactProtocol : 压缩格式
TJSONProtocol : JSON 格式
TSimpleJSONProtocol : 提供 JSON 只写协议, 生成的文件很容易通过脚本语言解析

## 参考资料

- [基于注解注册连接的 Thrift 框架(01) - 枫沰 - 博客园](https://www.cnblogs.com/cybersiren/p/18265995)
- [基于注解注册连接的 Thrift 框架(02)——TServer - 枫沰 - 博客园](https://www.cnblogs.com/cybersiren/p/18266128)
- [基于注解注册连接的 Thrift 框架(03)——TProcessor - 枫沰 - 博客园](https://www.cnblogs.com/cybersiren/p/18304739)
- [基于注解注册连接的 Thrift 框架(04)——TProtocol - 枫沰 - 博客园](https://www.cnblogs.com/cybersiren/p/18309361)
- [基于注解注册连接的 Thrift 框架(05)——TTransport - 枫沰 - 博客园](https://www.cnblogs.com/cybersiren/p/18309969)
- [RPC 框架初体验之 Thrift_thrift 各版本说明-CSDN 博客](https://blog.csdn.net/shirukai/article/details/99950390)
- [Thrift 入门及 Java 实例演示 thrift thriftfield thriftstruct java-CSDN 博客](https://blog.csdn.net/z69183787/article/details/51333001)

- 🌟 Thrift 使用配置文档[Apache Thrift 学习之一（入门及 Java 实例演示） - duanxz - 博客园](https://www.cnblogs.com/duanxz/p/5516558.html)
- 示例仓库 [ostenant/spring-cloud-starter-thrift: spring-cloud-starter-thrift 提供 SpringCloud 对可伸缩的跨语言服务调用框架 Apache Thrift 的封装和集成。](https://github.com/ostenant/spring-cloud-starter-thrift)
- 相关教程 [Spring Cloud 整合 Thrift RPC(一) - 使用指南前面几篇博客，着重对 Apache Thrift 的使用 - 掘金](https://juejin.cn/post/6844903623080558599)
