---
type: basic-note
title: servlet-tomcat
author: JackyLee
create_time: 2025-10-23
update_time:
tags:
description:
---

## web-servlet 注解

可以写 `/user` 或者 `/user/hello` 或者 `/user/*` 甚至 `/*`

也可以写 `*.do` 可以匹配 `/foo.do` 或者 `/bar.do`(不要写成 `/*.do`，会报错)

## servlet 体系结构

- Servlet 接口
- GenericServlet 抽象类
- HttpServlet 抽象类

### Request 体系结构

- ServletRequest 接口
  | 继承
- HttpServletRequest 接口
  | 继承
- org.apache.catalina.connector.RequestFacade 实现

## web.xml 作用

我们浏览器输入 `localhost:8080/servlet/user`

1. 通过 `localhost:8080` 找到 tomcat 主机
2. tomcat 从 webapp 中寻找 servlet.war 包下寻找 web.xml
3. 从 web.xml 解析并从 servlet-mapping 中寻找 `/user`，并找到 servlet-name
4. 根据 servlet-name 寻找全类名的字节码并加载进内存 (Class.forName(...))
5. 调用该类的 service 方法
6. tomcat 将 request 和 response 对象传给 service 方法, 并调用该方法
7. 我们可以设置 response 对象

tomcat 负责创建对象并调用方法
使用了动态代理技术

- 查看下载哪个版本的 tomcat [Apache Tomcat® - Which Version Do I Want?](https://tomcat.apache.org/whichversion.html)
- [【VScode】Windows10 上用 Community Server Connector 配置 Tomcat 并部署 Maven 项目 - 知乎](https://zhuanlan.zhihu.com/p/586056834)

- [Servlet 入门 - Java 教程 - 廖雪峰的官方网站](https://liaoxuefeng.com/books/java/web/servlet-basic/index.html)

## 参考资料
