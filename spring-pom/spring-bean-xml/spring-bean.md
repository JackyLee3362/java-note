---
type: basic-note
title: Spring-day01
author: JackyLee
create_date: 2023-05-20
update_date:
tags:
description: 2023-05-20 黑马程序员
---

## 1 主要内容

特性： IOC AOP(处理事务)

框架整合:

- MyBatis
- MyBatis-plus
- Struts
- Struts2
- Hibernate
- ……

## 2 Spring 相关概念

#### 2.1.1 Spring 家族

- [Spring | Home](https://spring.io/)

  - web 开发、微服务、分布式系统
  - Spring 全家桶: 查看官网 project 查看所有技术

    - 关注 `Spring Framework`、`SpringBoot`和`SpringCloud`:
    - Spring Framework:Spring 框架，是 Spring 中最早最核心的技术，也是所有其他技术的基础。
    - SpringBoot:Spring 是来简化开发，而 SpringBoot 是来帮助 Spring 在简化的基础上能更快速进行开发。
    - SpringCloud:这个是用来做分布式之微服务架构的相关开发。

    - 其他的比如也比较流行，如 SpringData,SpringSecurity 等

#### 2.1.2 了解 Spring 发展史

接下来我们介绍下 Spring Framework 这个技术是如何来的呢?

![image-20210729171926576](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151020916.png)

Spring 发展史

- IBM(IT 公司-国际商业机器公司)在 1997 年提出了 EJB 思想,早期的 JAVAEE 开发大都基于该思想。
- Rod Johnson(Java 和 J2EE 开发领域的专家)在 2002 年出版的`Expert One-on-One J2EE Design and Development`,书中有阐述在开发中使用 EJB 该如何做。
- Rod Johnson 在 2004 年出版的`Expert One-on-One J2EE Development without EJB`,书中提出了比 EJB 思想更高效的实现方案，并且在同年将方案进行了具体的落地实现，这个实现就是 Spring1.0。
- 随着时间推移，版本不断更新维护，目前最新的是 Spring5
  - Spring1.0 是纯配置文件开发
  - Spring2.0 为了简化开发引入了注解开发，此时是配置文件加注解的开发方式
  - Spring3.0 已经可以进行纯注解开发，使开发效率大幅提升，我们的课程会以注解开发为主
  - Spring4.0 根据 JDK 的版本升级对个别 API 进行了调整
  - Spring5.0 已经全面支持 JDK8，现在 Spring 最新的是 5 系列所以建议大家把 JDK 安装成 1.8 版

### 2.2 Spring 系统架构

前面我们说 spring 指的是 Spring Framework,那么它其中都包含哪些内容以及我们该如何学习这个框架?

针对这些问题，我们将从`系统架构图`和`课程学习路线`来进行说明:

#### 2.2.1 系统架构图

![image-20210729172153796](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151020917.png)

Spring Framework 的 5 版本目前没有最新的架构图，而最新的是 4 版本，所以接下来主要研究的是 4 的架构图

![1629720945720](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151020918.png)

1. 核心层
   - Core Container:核心容器，这个模块是 Spring 最核心的模块，其他的都需要依赖该模块
2. AOP 层
   - AOP:面向切面编程，它依赖核心层容器，目的是==在不改变原有代码的前提下对其进行功能增强==
   - Aspects:AOP 是思想,Aspects 是对 AOP 思想的具体实现
3. 数据层
   - Data Access:数据访问，Spring 全家桶中有对数据访问的具体实现技术
   - Data Integration:数据集成，Spring 支持整合其他的数据层解决方案，比如 Mybatis
   - Transactions:事务，Spring 中事务管理是 Spring AOP 的一个具体实现，也是后期学习的重点内容
4. Web 层
   - 这一层的内容将在 SpringMVC 框架具体学习
5. Test 层
   - Spring 主要整合了 Junit 来完成单元测试和集成测试

#### 2.2.2 学习路线

介绍完 Spring 的体系结构后，从中我们可以得出对于 Spring 的学习主要包含四部分内容，分别是:

- IOC / DI
- AOP
- AOP 的具体应用,事务管理
- IOC/DI 的具体应用,整合 Mybatis

## 2.3 Spring 核心概念

### 2.3.2 IOC、IOC 容器、Bean、DI

1. ==IOC（Inversion of Control）控制反转==

(1)什么是控制反转呢？

- 使用对象时，由主动 new 产生对象转换为由==外部==提供对象，此过程中对象创建控制权由程序转移到外部，此思想称为控制反转。
  - 业务层要用数据层的类对象，以前是自己`new`的
  - 现在自己不 new 了，交给`别人[外部]`来创建对象
  - `别人[外部]`就反转控制了数据层对象的创建权
  - 这种思想就是控制反转
  - 别人[外部]指定是什么呢?继续往下学

(2)Spring 和 IOC 之间的关系是什么呢?

- Spring 技术对 IOC 思想进行了实现
- Spring 提供了一个容器，称为==IOC 容器==，用来充当 IOC 思想中的"外部"
- IOC 思想中的`别人[外部]`指的就是 Spring 的 IOC 容器

(3)IOC 容器的作用以及内部存放的是什么?

- IOC 容器负责对象的创建、初始化等一系列工作，其中包含了数据层和业务层的类对象
- 被创建或被管理的对象在 IOC 容器中统称为==Bean==
- IOC 容器中放的就是一个个的 Bean 对象

(4)当 IOC 容器中创建好 service 和 dao 对象后，程序能正确执行么?

- 不行，因为 service 运行需要依赖 dao 对象
- IOC 容器中虽然有 service 和 dao 对象
- 但是 service 对象和 dao 对象没有任何关系
- 需要把 dao 对象交给 service,也就是说要绑定 service 和 dao 对象之间的关系

像这种在容器中建立对象与对象之间的绑定关系就要用到 DI:

2. ==DI（Dependency Injection）依赖注入==

![1629735078619](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151020922.png)

(1)什么是依赖注入呢?

- 在容器中建立 bean 与 bean 之间的依赖关系的整个过程，称为依赖注入
  - 业务层要用数据层的类对象，以前是自己`new`的
  - 现在自己不 new 了，靠`别人[外部其实指的就是IOC容器]`来给注入进来
  - 这种思想就是依赖注入

(2)IOC 容器中哪些 bean 之间要建立依赖关系呢?

- 这个需要程序员根据业务需求提前建立好关系，如业务层需要依赖数据层，service 就要和 dao 建立依赖关系

介绍完 Spring 的 IOC 和 DI 的概念后，我们会发现这两个概念的最终目标就是:==充分解耦==，具体实现靠:

- 使用 IOC 容器管理 bean（IOC)
- 在 IOC 容器内将有依赖关系的 bean 进行关系绑定（DI）
- 最终结果为:使用对象时不仅可以直接从 IOC 容器中获取，并且获取到的 bean 已经绑定了所有的依赖关系.

#### 2.3.3 核心概念小结

这节比较重要，重点要理解`什么是IOC/DI思想`、`什么是IOC容器`和`什么是Bean`：

(1)什么 IOC/DI 思想?

- IOC:控制反转，控制反转的是对象的创建权
- DI:依赖注入，绑定对象与对象之间的依赖关系

(2)什么是 IOC 容器?

Spring 创建了一个容器用来存放所创建的对象，这个容器就叫 IOC 容器

(3)什么是 Bean?

容器中所存放的一个个对象就叫 Bean 或 Bean 对象

## 5 DI 相关内容

前面我们已经完成了 bean 相关操作的讲解，接下来就进入第二个大的模块`DI依赖注入`，首先来介绍下 Spring 中有哪些注入方式?

我们先来思考

- 向一个类中传递数据的方式有几种?
  - 普通方法(set 方法)
  - 构造方法
- 依赖注入描述了在容器中建立 bean 与 bean 之间的依赖关系的过程，如果 bean 运行需要的是数字或字符串呢?
  - 引用类型
  - 简单类型(基本数据类型与 String)

Spring 就是基于上面这些知识点，为我们提供了两种注入方式，分别是:

- setter 注入
  - 简单类型
  - ==引用类型==
- 构造器注入
  - 简单类型
  - 引用类型

#### 5.1.3 注入简单数据类型

### 5.2 构造器注入

介绍完setter注入和构造器参数两种注入方式，具体我们该如何选择呢?

1. 强制依赖使用构造器进行，使用 setter 注入有概率不进行注入导致 null 对象出现
   - 强制依赖指对象在创建的过程中必须要注入指定的参数
2. 可选依赖使用 setter 注入进行，灵活性强
   - 可选依赖指对象在创建过程中注入的参数可有可无
3. Spring 框架倡导使用构造器，第三方框架内部大多数采用构造器注入的形式进行数据初始化，相对严谨
4. 如果有必要可以两者同时使用，使用构造器注入完成强制依赖的注入，使用 setter 注入完成可选依赖的注入
5. 实际开发过程中还要根据实际情况分析，如果受控对象没有提供 setter 方法就必须使用构造器注入
6. **==自己开发的模块推荐使用 setter 注入==**

<!-- day02 内容 -->

目标
- 掌握 IOC/DI 配置管理第三方 bean
- 掌握 IOC/DI 的注解开发
- 掌握 IOC/DI 注解管理第三方 bean
- 完成 Spring 与 Mybatis 及 Junit 的整合开发

## 1，IOC/DI 配置管理第三方 bean

前面所讲的知识点都是基于我们自己写的类，现在如果有需求让我们去管理第三方 jar 包中的类，该如何管理?

### 1.1 案例:数据源对象管理

在这一节中，我们将通过一个案例来学习下对于第三方 bean 该如何进行配置管理。

以后我们会用到很多第三方的 bean,本次案例将使用咱们前面提到过的数据源`Druid(德鲁伊)`和`C3P0`来配置学习下。

#### 1.1.3 实现 Druid 管理

带着这两个问题，把下面的案例实现下:

##### 步骤 1:导入`druid`的依赖

pom.xml 中添加依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.16</version>
</dependency>
```

##### 步骤 2:配置第三方 bean

在 applicationContext.xml 配置文件中添加`DruidDataSource`的配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!--管理DruidDataSource对象-->
    <bean class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/spring_db"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>
</beans>
```

**说明:**

- driverClassName:数据库驱动
- url:数据库连接地址
- username:数据库连接用户名
- password:数据库连接密码
- 数据库连接的四要素要和自己使用的数据库信息一致。

##### 步骤 3:从 IOC 容器中获取对应的 bean 对象

```java
public class App {
    public static void main(String[] args) {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       DataSource dataSource = (DataSource) ctx.getBean("dataSource");
       System.out.println(dataSource);
    }
}
```

##### 步骤 4:运行程序

打印如下结果: 说明第三方 bean 对象已经被 spring 的 IOC 容器进行管理

![1629887733081](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022160.png)

做完案例后，我们可以将刚才思考的两个问题答案说下:

- 第三方的类指的是什么?

  ```
  DruidDataSource
  ```

- 如何注入数据库连接四要素?

  ```
  setter注入
  ```

#### 1.1.4 实现 C3P0 管理

完成了 DruidDataSource 的管理，接下来我们再来加深下练习，这次我们来管理`C3P0`数据源，具体的实现步骤是什么呢?

> 需求:使用 Spring 的 IOC 容器来管理 C3P0 连接池对象
>
> 实现方案和上面基本一致，重点要关注管理的是哪个 bean 对象`?

##### 步骤 1:导入`C3P0`的依赖

pom.xml 中添加依赖

```xml
<dependency>
    <groupId>c3p0</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.1.2</version>
</dependency>
```

**对于新的技术，不知道具体的坐标该如何查找?**

- 直接百度搜索

- 从 mvn 的仓库`https://mvnrepository.com/`中进行搜索

  ![1629888540286](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022161.png)

##### 步骤 2:配置第三方 bean

在 applicationContext.xml 配置文件中添加配置

```xml
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/spring_db"/>
    <property name="user" value="root"/>
    <property name="password" value="root"/>
    <property name="maxPoolSize" value="1000"/>
</bean>
```

**==注意:==**

- ComboPooledDataSource 的属性是通过 setter 方式进行注入
- 想注入属性就需要在 ComboPooledDataSource 类或其上层类中有提供属性对应的 setter 方法
- C3P0 的四个属性和 Druid 的四个属性是不一样的

##### 步骤 3:运行程序

程序会报错，错误如下

![1629889170229](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022162.png)

报的错为==ClassNotFoundException==,翻译出来是`类没有发现的异常`，具体的类为`com.mysql.jdbc.Driver`。错误的原因是缺少 mysql 的驱动包。

分析出错误的原因，具体的解决方案就比较简单，只需要在 pom.xml 把驱动包引入即可。

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
```

添加完 mysql 的驱动包以后，再次运行 App,就可以打印出结果:

![1629903845404](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022163.png)

**注意：**

- 数据连接池在配置属性的时候，除了可以注入数据库连接四要素外还可以配置很多其他的属性，具体都有哪些属性用到的时候再去查，一般配置基础的四个，其他都有自己的默认值
- Druid 和 C3P0 在没有导入 mysql 驱动包的前提下，一个没报错一个报错，说明 Druid 在初始化的时候没有去加载驱动，而 C3P0 刚好相反
- Druid 程序运行虽然没有报错，但是当调用 DruidDataSource 的 getConnection()方法获取连接的时候，也会报找不到驱动类的错误

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="
              http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans.xsd
              http://www.springframework.org/schema/context
              http://www.springframework.org/schema/context/spring-context.xsd">

      <context:property-placeholder location="jdbc.properties" system-properties-mode="NEVER"/>
  </beans>
  ```

  system-properties-mode:设置为 NEVER,表示不加载系统属性，就可以解决上述问题。

  当然还有一个解决方案就是避免使用`username`作为属性的`key`。

- 问题二:当有多个 properties 配置文件需要被加载，该如何配置?

  1.调整下配置文件的内容，在 resources 下添加`jdbc.properties`,`jdbc2.properties`,内容如下:

  jdbc.properties

  ```ini
  jdbc.driver=com.mysql.jdbc.Driver
  jdbc.url=jdbc:mysql://127.0.0.1:3306/spring_db
  jdbc.username=root
  jdbc.password=root
  ```

  jdbc2.properties

  ```ini
  username=root666
  ```

  2.修改 applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="
              http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans.xsd
              http://www.springframework.org/schema/context
              http://www.springframework.org/schema/context/spring-context.xsd">
      <!--方式一 -->
      <context:property-placeholder location="jdbc.properties,jdbc2.properties" system-properties-mode="NEVER"/>
      <!--方式二-->
      <context:property-placeholder location="*.properties" system-properties-mode="NEVER"/>
      <!--方式三 -->
      <context:property-placeholder location="classpath:*.properties" system-properties-mode="NEVER"/>
      <!--方式四-->
      <context:property-placeholder location="classpath*:*.properties" system-properties-mode="NEVER"/>
  </beans>
  ```

  **说明:**

  - 方式一:可以实现，如果配置文件多的话，每个都需要配置
  - 方式二:`*.properties`代表所有以 properties 结尾的文件都会被加载，可以解决方式一的问题，但是不标准
  - 方式三:标准的写法，`classpath:`代表的是从根路径下开始查找，但是只能查询当前项目的根路径
  - 方式四:不仅可以加载当前项目还可以加载当前项目所依赖的所有项目的根路径下的 properties 配置文件

#### 1.2.3 加载 properties 文件小结

本节主要讲解的是 properties 配置文件的加载，需要掌握的内容有:

- 如何开启`context`命名空间

  ![1629980280952](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022166.png)

- 如何加载 properties 配置文件

  ```xml
  <context:property-placeholder location="" system-properties-mode="NEVER"/>
  ```

- 如何在 applicationContext.xml 引入 properties 配置文件中的值

  ```
  ${key}
  ```

## 3，IOC/DI 注解开发

### 3.4 注解开发依赖注入

Spring 为了使用注解简化开发，并没有提供`构造函数注入`、`setter注入`对应的注解，只提供了自动装配的注解实现。

**注意:**

- @Autowired 可以写在属性上，也可也写在 setter 方法上，最简单的处理方式是`写在属性上并将setter方法删除掉`
- 为什么 setter 方法可以删除呢?
  - 自动装配基于反射设计创建对象并通过暴力反射为私有属性进行设值
  - 普通反射只能获取 public 修饰的内容
  - 暴力反射除了获取 public 修饰的内容还可以获取 private 修改的内容
  - 所以此处无需提供 setter 方法

(2)@Autowired 是按照类型注入，那么对应 BookDao 接口如果有多个实现类，比如添加 BookDaoImpl2

```java
@Repository
public class BookDaoImpl2 implements BookDao {
    public void save() {
        System.out.println("book dao save ...2");
    }
}
```

这个时候再次运行 App，就会报错

![1630034272959](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022187.png)

此时，按照类型注入就无法区分到底注入哪个对象，解决方案:`按照名称注入`

- 先给两个 Dao 类分别起个名称

  ```java
  @Repository("bookDao")
  public class BookDaoImpl implements BookDao {
      public void save() {
          System.out.println("book dao save ..." );
      }
  }
  @Repository("bookDao2")
  public class BookDaoImpl2 implements BookDao {
      public void save() {
          System.out.println("book dao save ...2" );
      }
  }
  ```

  此时就可以注入成功，但是得思考个问题:

  - @Autowired 是按照类型注入的，给 BookDao 的两个实现起了名称，它还是有两个 bean 对象，为什么不报错?

  - @Autowired 默认按照类型自动装配，如果 IOC 容器中同类的 Bean 找到多个，就按照变量名和 Bean 的名称匹配。因为变量名叫`bookDao`而容器中也有一个`booDao`，所以可以成功注入。

  - 分析下面这种情况是否能完成注入呢?

    ![1630036236150](https://assets-1302294329.cos.ap-shanghai.myqcloud.com/2025/md/202505151022188.png)

  - 不行，因为按照类型会找到多个 bean 对象，此时会按照`bookDao`名称去找，因为 IOC 容器只有名称叫`bookDao1`和`bookDao2`,所以找不到，会报`NoUniqueBeanDefinitionException`

#### 3.4.3 注解实现按照名称注入

当根据类型在容器中找到多个 bean,注入参数的属性名又和容器中 bean 的名称不一致，这个时候该如何解决，就需要使用到`@Qualifier`来指定注入哪个名称的 bean 对象。

```java
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    @Qualifier("bookDao1")
    private BookDao bookDao;

    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
```

@Qualifier 注解后的值就是需要注入的 bean 的名称。

==注意:@Qualifier 不能独立使用，必须和@Autowired 一起使用==

