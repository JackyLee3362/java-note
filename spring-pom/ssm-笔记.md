---
type: basic-note
title: ssm
author: JackyLee
create_date: 2025-09-30
update_date:
tags:
description: 黑马程序员
---

[课程](https://www.bilibili.com/video/BV1Fi4y1S7ix)

## Spring Bean

### p02 课程介绍

学什么 IoC 和 AOP

### p04 Spring系统架构

#### 08:28 SpringFramework 系统架构

![p04-08-28-SpringFramework系统架构](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251018.png)

### p05 核心概念

Ioc: Inversion of Control 控制反转：对象的控制建议权转移到外部

#### 02:59 IoC 解释

![p05-02-59-ioc解释](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251450.png)

#### 07:48 IoC 解释

![p05-07-48-ioc解释](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251729.png)

### p06 IoC 入门案例

#### 11:21 IoC 入门案例 xml

![p06-11-21-ioc入门案例xml](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008873.png)

#### 11:27 IoC 入门案例 xml2

![p06-11-27-ioc入门案例xml2](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251137.png)

#### pom.xml 的基本写法

```xml
<?xml version="1.0" encoding="utf-8" ?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>springbean</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</project>
```

### p07 DI 入门案例

#### 06:22 DI 入门案例

![p07-06-22-DI入门案例](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251372.png)

### p08 bean 基础配置

默认单例，更改为非单例`prototype`

```xml
<bean id="bookDao" class="com.example.dao.impl.BookDaoimpl" scope="prototype"/>
```

#### 01:02 bean 基础配置

![p08-01-02-bean基础配置](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008874.png)

#### 06:59 bean 作用范围配置

![p08-06-59-bean作用范围配置](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251889.png)

### p09 bean 实例化——构造方法

#### 06:14 实例化 bean 的三种方式构造方法（常用）

![p09-06-14-实例化bean的三种方式-构造方法（常用）](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150251601.png)

### p10 bean 实例化-静态工厂

#### 04:15 实例化 bean 的三种方式静态工厂（了解）

![p10-04-15-实例化bean的三种方式-静态工厂（了解）](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505150252842.png)

### p11 实例工厂与 FactoryBean

#### 05:07 实例化 bean 的三种方式实例工厂（了解）

![p11-05-07-实例化bean的三种方式-实例工厂（了解）](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008875.png)

#### 10:11 实例化 bean 的第四种方式 FactoryBean

![p11-10-11-实例化bean的第四种方式-FactoryBean](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008876.png)

### p12 bean 的生命周期

#### 03:55 配置 init 和 destory 方法运行后的问题

![p12-03-55-配置init和destory方法运行后的问题](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008877.png)

#### 06:16 配置 init 和 destory 方法运行后的问题解决方案 1

![p12-06-16-配置init和destory方法运行后的问题-解决方案1](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008878.png)

#### 07:53 spring 标准配置 init 和 destory

![p12-07-53-spring标准配置init和destory](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008879.png)

#### 07:53 配置 init 和 destory 方法运行后的问题解决方案 2

![p12-07-53-配置init和destory方法运行后的问题-解决方案2](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008880.png)

#### 12:30 bean 生命周期

![p12-12-30-bean生命周期](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008881.png)

#### 13:40 bean 生命周期控制

![p12-13-40-bean生命周期控制](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008882.png)

#### 13:40 bean 销毁时期

![p12-13-40-bean销毁时期](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008883.png)

### p13 setter 注入

#### 02:18 依赖注入方式 4 种

![p13-02-18-依赖注入方式4种](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008884.png)

#### 07:52 依赖注入 setter 注入简单类型

![p13-07-52-依赖注入-setter注入-简单类型-案例](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008885.png)

#### 08:17 依赖注入 setter 注入引用类型

![p13-08-17-依赖注入-setter注入-引用类型](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008886.png)

#### 08:23 依赖注入 setter 注入简单类型

![p13-08-23-依赖注入-setter注入-简单类型](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008887.png)

### p14 构造器注入

#### 04:08 依赖注入构造器注入引用类型

![p14-04-08-依赖注入-构造器注入-引用类型](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008888.png)

#### 05:24 依赖注入构造器注入简单和引用类型

![p14-05-24-依赖注入-构造器注入-简单和引用类型](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008889.png)

#### 07:57 依赖注入构造器注入去掉 name

![p14-07-57-依赖注入-构造器注入-去掉name](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008890.png)

#### 14:21 依赖注入方式小结

![p14-14-21-依赖注入方式-小结](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008891.png)

### p15 自动装配

#### 02:46 自动装配 xml 配置怎么写

![p15-02-46-自动装配-xml配置怎么写](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008892.png)

#### 09:18 依赖自动装配特征

![p15-09-18-依赖自动装配特征](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008893.png)

### p16 集合注入

#### 02:30 集合注入 array 和 list

![p16-02-30-集合注入-array和list](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008894.png)

#### 03:55 集合注入 set 和 map

![p16-03-55-集合注入-set和map](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008895.png)

#### 04:50 集合注入 properties

![p16-04-50-集合注入-properties](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008896.png)

### p17 案例-数据源对象管理

#### 05:34 第三方 bean 管理

![p17-05-34-第三方bean管理](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008897.png)

#### 13:11 数据源对象管理

![p17-13-11-数据源对象管理](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008898.png)

### p18 加载 properties 文件

#### 02:38 开辟新的命名空间

![p18-02-38-开辟新的命名空间](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008899.png)

#### 04:58 properties 使用 bean 示例

![p18-04-58-properties使用bean示例](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008900.png)

#### 06:36 加载 properties 文件

![p18-06-36-加载properties文件](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008901.png)

#### 12:02 导入 properties 标准写法

![p18-12-02-导入properties标准写法](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008902.png)

#### 12:13 加载 properties 文件

![p18-12-13-加载properties文件](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008903.png)

### p19 容器

#### 05:25 创建容器三种方式

![p19-05-25-创建容器-三种方式](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008904.png)

#### 05:40 获取 bean 的三种方式

![p19-05-40-获取bean的三种方式](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008905.png)

#### 12:33 容器类层次结构图

![p19-12-33-容器类层次结构图](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008906.png)

#### 13:31 BeanFactory 初始化

![p19-13-31-BeanFactory初始化](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008907.png)

### p20 核心容器总结

#### 01:17 容器相关

![p20-01-17-容器相关](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008908.png)

#### 01:33 bean 相关

![p20-01-33-bean相关](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008909.png)

#### 03:05 依赖注入相关

![p20-03-05-依赖注入相关](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008910.png)

### p21 注解开发定义 bean

#### 00:58 注解开发

![p21-00-58-注解开发](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008911.png)

#### 06:52 注解开发定义 bean

![p21-06-52-注解开发定义bean](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008912.png)

### p22 纯注解开发模式

#### 04:23 纯注解开发

![p22-04-23-纯注解开发](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008913.png)

#### 05:50 纯注解开发加载容器

![p22-05-50-纯注解开发-加载容器](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008914.png)

### p23 注解开发 bean 作用范围与生命周期管理

#### 04:13 bean 生命周期

![p23-04-13-bean生命周期](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008915.png)

### p24 注解开发依赖注入

#### 05:34 依赖注入

![p24-05-34-依赖注入](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008916.png)

#### 06:22 依赖注入（如果存在两个 impl 怎么办，用 atQ）

![p24-06-22-依赖注入（如果存在两个impl怎么办，用atQ）](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008917.png)

#### 07:40 依赖注入简单类型 Value

![p24-07-40-依赖注入-简单类型Value](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008918.png)

#### 11:52 加载 properties 文件

![p24-11-52-加载properties文件](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008919.png)

### p25 注解开发管理第三方 bean

#### 00:30 第三方 bean 管理

![p25-00-30-第三方bean管理](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008920.png)

#### 04:54 第三方 bean 管理示例

![p25-04-54-第三方bean管理示例](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008921.png)

#### 07:15 拆分 Bean 到另一个配置类中

![p25-07-15-拆分Bean到另一个配置类中](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008922.png)

#### 07:35 第三方 bean 管理

![p25-07-35-第三方bean管理](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008923.png)

### p26 注解开发实现为第三方 bean 注入资源

#### 03:59 第三方 bean 依赖注入简单类型

![p26-03-59-第三方bean依赖注入-简单类型](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008924.png)

### p27 注解开发总结

#### 00:25 XML 配置对比注解配置（重点几个）

![p27-00-25-XML配置对比注解配置（重点几个）](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008925.png)

#### 04:25 XML 配置对比注解配置

![p27-04-25-XML配置对比注解配置](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008927.png)

### p28 spring 整合 mybatis 思路分析

#### 02:37 Spring 整合 MyBatis

![p28-02-37-Spring整合MyBatis](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008928.png)

#### 04:29 Spring 整合 MyBatis

![p28-04-29-Spring整合MyBatis](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008929.png)

#### 04:55 xml 配置

![p28-04-55-xml配置](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008930.png)

#### 05:05 xml 配置

![p28-05-05-xml配置](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008931.png)

### p29 spring 整合 MyBatis

#### 18:15 Spring 整合 MyBatis

![p29-18-15-Spring整合MyBatis](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008932.png)

### p30 Spring 整合 JUnit

#### 05:23 整合 Junit

![p30-05-23-整合Junit](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008933.png)

### p31 AOP 简介

#### 05:46 AOP 简介

![p31-05-46-AOP简介](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008934.png)

#### 10:21 AOP 核心概念

![p31-10-21-AOP核心概念](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008935.png)

#### 10:54 AOP 核心概念

![p31-10-54-AOP核心概念](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008936.png)

### p33 AOP 工作流程

#### 00:21 AOP 工作流程

![p33-00-21-AOP工作流程](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008937.png)

#### 06:02 AOP 工作流程

![p33-06-02-AOP工作流程](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008938.png)

### p37 AOP 通知获取数据

#### 08:39 aop 中如果增强鲁棒性

![p37-08-39-aop中如果增强鲁棒性](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008939.png)

#### 13:59 AOP 通知获取参数数据

![p37-13-59-AOP通知获取参数数据](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008940.png)

### p38 案例-百度网盘密码数据兼容处理

Service 类中

```java
    @Transactional
    public void transfer(Integer in, Integer out, Integer age) {
        userDao.inAge(in, age);
        int a = 1/0;
        userDao.outAge(out, age);
    }
```

### p42 spring 事务属性

#### 15:33 事务传播行为

![p42-15-33-事务传播行为](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008941.png)

## SpringMVC

### p44 SpringMVC 简介

#### 00:37 SpringMVC 入门案例

![p44-00-37-SpringMVC入门案例](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008942.png)

#### 11:59 ServletContainerInitConfig 类中的配置

![image-20230522134715587](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008943.png)

#### 18:30 ServletContainerInitConfig 类中的配置

![image-20230522142052418](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008944.png)

### p45 入门案例工作流程

#### 2:49 Spring 和 SpringMVC 的 Config 分开加载

![image-20230522142819558](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008945.png)

#### 4:17 SpringMVC 工作流程

![image-20230522142513948](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008946.png)

### P46 bean 加载控制

#### 9:30 第二种过滤方法

注释掉的是第一种

![image-20230522145042184](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008947.png)

#### 17:21 ServletContainerInitConf 类的配置

![image-20230522145509625](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008948.png)

### p49 get 请求与 post 请求发送普通参数

#### 05:32 GET 请求

![image-20230522151043251](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008949.png)

#### 5:46 POST 请求参数

![image-20230522151436641](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008950.png)

#### 8:41 过滤器

![image-20230522151907026](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008951.png)

### p50 5 种参数类型传递

#### 2:24 形参名和 Get 请求过来的名字不一样时怎么处理？

加`@RequestParam('name')`注解

![image-20230522152554136](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008952.png)

### p51 json 数据传递

- 第一步：`pom.xml`导入`jackyson-databind`坐标
- 第二步：`SpringMvcConfif`加上`@EnableWebMvc`

### p52 日期型参数传递

#### 04:04 日期格式的传递

![image-20230524131118353](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008953.png)

#### 05:31 日期格式的传递

#### ![image-20230524131214708](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008954.png)7:47 类型转换器

![image-20230524131518444](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008955.png)

### P53 响应

如果不加`@ResponseBody`，返回的就是页面

#### 07：20

![image-20230524132707659](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008956.png)

#### 09：04

转换接口

![image-20230524132903475](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008957.png)

### P54 REST 风格简介

#### 06:28 REST 风格

![image-20230524133336262](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008958.png)

### p55 RESTful 入门案例

#### 07:56 请求方法

![image-20230524140010247](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008959.png)

#### 08:31 路径变量

![image-20230524140042380](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008960.png)

#### 09：28 三个参数的区别

![image-20230524140141097](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008961.png)

### p58 基于 RESTful 页面数据交互

#### 6：30 静态资源的访问，设置 MVC 不处理

![image-20230524143325418](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008962.png)

### p63 表现层与前端数据传输

#### 12：52

![image-20230525151510510](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008963.png)

### p64 异常处理器

#### 2:10 异常处理器

![image-20230525151650619](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008964.png)

#### 8：00 spring 自带的异常处理器

![image-20230525152515965](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008965.png)

### p65 项目异常处理

#### 3:12

![image-20230525154718629](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008966.png)

#### 04:07

![image-20230525154755929](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008967.png)

#### 18:47 异常处理步骤

![image-20230525155600344](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008968.png)

### p66 前后台协议联调 未看

### p67 前后台协议联调 未看

### p68 前后台协议联调 未看

### p69 前后台协议联调 未看

### p70 前后台协议联调 未看

### p71 拦截器简介

#### 3:58 其实就是拦截器

![image-20230525181514426](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008969.png)

### p72 拦截器入门案例

#### 13:34

![image-20230525184845407](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008970.png)

#### 16:03

![image-20230525185122775](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008971.png)

### p74 拦截器-拦截器链配置

#### 05:41

![image-20230525185855055](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008972.png)

## Maven 教程

### p75 分模块开发与设计

#### 04:58

![image-20230525193300341](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008973.png)

#### 7:49

![image-20230525194917609](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008974.png)

### p78 可选依赖与排除依赖

#### 2:14

![image-20230525195435924](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008975.png)

#### 06:03

![image-20230525195916542](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008976.png)

### p79 聚合

#### 08:08

![image-20230525200427398](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008977.png)

#### 08:16

![image-20230525200440821](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008978.png)

### p83 版本管理

#### 2:34

![image-20230525202429969](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008979.png)

### p84 多环境开发

#### 4:13

![image-20230525202743938](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008980.png)

#### 06:32

![image-20230525202904171](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008981.png)

#### 06:53

![image-20230525202923489](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008982.png)

### p85 跳过测试

#### 06:52 多环境配置与应用

![image-20230525203320036](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008983.png)

#### 07：09

![image-20230525203340566](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008984.png)

### p86 私服简介与安装

#### 8:40

![image-20230525203838344](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008985.png)

### p87 私服仓库分类

#### 5:34

![image-20230525204224371](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008986.png)

## SpringBoot

### p92 SpringBoot 程序快速启动

#### 3:50

![image-20230525234722725](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008987.png)

### p103 springboot 整合 mybatis

#### 16:06

![p103-16-06-springboot整合mybatis](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008988.png)

### P104 案例：

#### 12:56

![image-20230526004954624](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008989.png)

## MyBatis-Plus

### p105 入门案例

#### 13:11

![image-20230526005736012](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008990.png)

#### 13:37

![image-20230526005806919](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008991.png)

### p107 标准 CRUD 制作

#### 00:46

#### ![image-20230526122932247](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008992.png)11:03

![image-20230526123432531](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008993.png)

### p108 标准分页功能制作

#### 4:13

![image-20230526123833131](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008994.png)

#### 7:51 mp 拦截器配置

![image-20230526124340098](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008995.png)

#### 10:18 输出到控制台的日志

![image-20230526124423721](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008996.png)

### p109 条件查询的三种方式

使用`QueryWrapper`

#### 8:01 方式一 ：

![image-20230526132418173](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008997.png)

#### 9：22 方式二：引用实体类的方法，实际传出的实体类的变量名

#### ![image-20230526132527396](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008998.png)10：54 方式三：对方式二简化

![image-20230526132703575](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008999.png)

#### 12：33

![image-20230526132832258](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008000.png)

#### 13：58 或的条件怎么写

![image-20230526132927751](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008001.png)

#### 14：36

![image-20230526132956145](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008002.png)

#### 14：57

![image-20230526133007124](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008003.png)

#### 15：12 条件查询-组合条件查询

```java

```

![image-20230526133017833](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008004.png)

### p110 条件查询 null 判定

#### 8:28 条件查询-null 处理

![image-20230526135704622](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008005.png)

### p111 查询投影

#### 6：05 查询投影

![image-20230526173813252](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008006.png)

#### 7:05 查询条件

![image-20230526174128761](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008007.png)

### p112 查询条件设置

#### 1:38 属性注解

![image-20230526174241236](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008008.png)

#### 2:56 未定义属性

![image-20230526174321966](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008009.png)

#### 4:25 字段映射注解

![image-20230526174410065](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008010.png)

#### 5:12 表名映射

![image-20230526174427382](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008011.png)

### p113 映射匹配兼容性

#### 2:30 id 生成策略控制

![image-20230526174555275](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008012.png)

### p114 id 生成策略

#### 14:58 id 生成策略全局配置

![image-20230526175353703](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008013.png)

#### 15:00 全局配置

![image-20230526175405641](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151008014.png)

### 115 多数据操作（删除与查询）未看

### 116 逻辑删除 未看

### 117 乐观锁 未看

### 118 代码生成器 未看
