---
title: spring 常见注解
tags:
  - 编程
  - java/spring
  - java/注解
created_time: 2024-10-20
---

## Intro 介绍

介绍 Spring 中常用的注解

### 知识点 1：@Autowired

| 名称 | @Autowired                                                           |
| ---- | -------------------------------------------------------------------- |
| 类型 | 属性注解 或 方法注解（了解） 或 方法形参注解（了解）                 |
| 位置 | 属性定义上方 或 标准 set 方法上方 或 类 set 方法上方 或 方法形参前面 |
| 作用 | 为引用类型属性设置值                                                 |
| 属性 | required：true/false，定义该属性是否允许为 null                      |

### 知识点 2：@Qualifier

| 名称 | @Qualifier                                           |
| ---- | ---------------------------------------------------- |
| 类型 | 属性注解 或 方法注解（了解）                         |
| 位置 | 属性定义上方 或 标准 set 方法上方 或 类 set 方法上方 |
| 作用 | 为引用类型属性指定注入的 beanId                      |
| 属性 | value（默认）：设置注入的 beanId                     |

### 知识点 3：@Value

| 名称 | @Value                                               |
| ---- | ---------------------------------------------------- |
| 类型 | 属性注解 或 方法注解（了解）                         |
| 位置 | 属性定义上方 或 标准 set 方法上方 或 类 set 方法上方 |
| 作用 | 为 基本数据类型 或 字符串类型 属性设置值             |
| 属性 | value（默认）：要注入的属性值                        |

#### 知识点 4：@PropertySource

| 名称 | @PropertySource                                                         |
| ---- | ----------------------------------------------------------------------- |
| 类型 | 类注解                                                                  |
| 位置 | 类定义上方                                                              |
| 作用 | 加载 properties 文件中的属性值                                          |
| 属性 | value（默认）：设置加载的 properties 文件对应的文件名或文件名组成的数组 |

### 知识点 1: Bean 的生命周期：@Scope

| 名称 | @Scope                                                                                           |
| ---- | ------------------------------------------------------------------------------------------------ |
| 类型 | 类注解                                                                                           |
| 位置 | 类定义上方                                                                                       |
| 作用 | 设置该类创建对象的作用范围<br/>可用于设置创建出的 bean 是否为单例对象                            |
| 属性 | value（默认）：定义 bean 作用范围，<br/>==默认值 singleton（单例），可选值 prototype（非单例）== |

### 知识点 1：@PostConstruct

| 名称 | @PostConstruct         |
| ---- | ---------------------- |
| 类型 | 方法注解               |
| 位置 | 方法上                 |
| 作用 | 设置该方法为初始化方法 |
| 属性 | 无                     |

### 知识点 2：@PreDestroy

| 名称 | @PreDestroy          |
| ---- | -------------------- |
| 类型 | 方法注解             |
| 位置 | 方法上               |
| 作用 | 设置该方法为销毁方法 |
| 属性 | 无                   |

**注意:**: @PostConstruct 和@PreDestroy 注解如果找不到，需要导入下面的 jar 包==

```java
<dependency>
  <groupId>javax.annotation</groupId>
  <artifactId>javax.annotation-api</artifactId>
  <version>1.3.2</version>
</dependency>
```

找不到的原因是，从 JDK9 以后 jdk 中的 javax.annotation 包被移除了，这两个注解刚好就在这个包中。
### 知识点 1:@Component 等

| 名称 | @Component/@Controller/@Service/@Repository |
| ---- | ------------------------------------------- |
| 类型 | 类注解                                      |
| 位置 | 类定义上方                                  |
| 作用 | 设置该类为 spring 管理的 bean               |
| 属性 | value（默认）：定义 bean 的 id              |

### 知识点 1：@Configuration

| 名称 | @Configuration                 |
| ---- | ------------------------------ |
| 类型 | 类注解                         |
| 位置 | 类定义上方                     |
| 作用 | 设置该类为 spring 配置类       |
| 属性 | value（默认）：定义 bean 的 id |

#### 知识点 2：@ComponentScan

| 名称 | @ComponentScan                                              |
| ---- | ----------------------------------------------------------- |
| 类型 | 类注解                                                      |
| 位置 | 类定义上方                                                  |
| 作用 | 设置 spring 配置类扫描路径，用于加载使用注解格式定义的 bean |
| 属性 | value（默认）：扫描路径，此路径可以逐层向下扫描             |

## Controller 中的注解

### @DateTimeFormat

配合日期对象使用

- [DateTimeFormat (Spring Framework 6.2.4 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/format/annotation/DateTimeFormat.html)

```java
@Contrller
class Controller{
    @GetMapping("/hello")
    public String hello(
    @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate begin,
    @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end){
        // 如果要查看具体 可以查看类 DateTimeFormatter
        return "hello, world";
    }
}
```

### @PathVariable

```java
@Controller
class Controller{
    @GetMapping("/hello/{id}")
    public String hello1(@PathVariable int id) {
        // 请求的路径类似 localhost:8080/hello/1
        return "hello, world1";
    }
    @GetMapping("hello/{ids}")
    public String hello2(@PathVariable int[] ids) {
        // 请求的路径类似 localhost:8080/hello/1,2,3
        // 这里也可以用【集合】来接收 List<Integer> ids
        return "hello, world2";
    }
}

```

### @RequestBody

接收 json 格式的参数

```java
@Controller
class Controller {
    @PostMapping("/hello")
    public String hello(@RequestBody Emp emp) {
        // 必须用对象来接收
        // 之前debug，参数是这样写的 (@RequestBody String name, int age, ...)
        // 会导致后面的参数全为null，然后name变成一整个json字符串
        return "hello";
    }
}
```

### @RequestParam

query 参数

```java
@Controller
class Controller {
    @GetMapping("/hello")
    public String page(
            @RequestParam(value = "pageNum", required = true, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
        // 列出来了@RequestParam的三个属性
        // value：表示在url的query-string部分的参数名，不写默认和后面接收的变量名一致
        return "hello";
    }
}
```

### @RequestPart

## 关于配置的注解

### @Value

获得配置文件中的值

```java
class Demo{
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
}
```

在`application.properties`中获得

```properties
aliyun.oss.bucketName=tlias-jacky
# 如果是获取环境变量
aliyun.oss.envBucketName=${BUCKET_NAME}
```

### @ConfigurationProperties

1. 首先必须 yml 中的配置名与类中的变量名保持一致
2. 引入依赖，有了这个依赖，就会有相应的提示信息

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

```yml
aliyun:
  oss:
    endpoint: https://oss-cn-beijing.aliyuncs.com
    accessKey: ${OSS_ACCESS_KEY_ID}
    accessKeySecret: ${OSS_ACCESS_KEY_SECRET}
    bucketName: tlias-jacky
```

```java
@Data
@Component
@ConfigurationProperties(prefix="aliyun.oss")
public class AliOSSUtils{
    private String endpoint;
    private String accessKey;
    private String accessKeySecret;
    private String bucketName;

}
```

## 异常处理

### @RestControllerAdvice 和 @ExceptionHandler

```java
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.error("操作错误，请练习管理员");
    }
}
```

## 数据库

### @Transactional

位于 Service 层上 / 类 / 接口

只有出现 `RuntimeException` 才会出现回滚，否则不会

要想设置成普通异常也回滚

可以变成 `@Transactional(rollbackFor=Exception.call)`

另一个属性

propagation

事务传播行为：指的就是当一个事务方法被另一个事务方法调用时，这个事务方法应该如何进行事务控制

## AOP

### 通知

- @Around : 环绕通知，有参数`ProceedingJointPoint p`，要调用 `p.proceed()`继续执行，返回值要是`Object`
- @Before : 前置通知
- @After : 后置通知，无论有异常都会被执行
- @AfterReturning : 返回后通知，有异常不会被执行
- @AfterThrowing : 异常后通知，有异常后会被执行

### @PointCut

```java
public class Demo{
    // 抽取公共的切入点表达式
    // private: 只能在当前类中用
    // public : 其他外部切面类也可以使用
    @Pointcut("execution(* com.jacky.service.*.*(..))")
    private void pt() {
    }

    @Around("pt()")
    public Object Timer(ProceedingJoinPoint p)
            throws Throwable{
        // 执行前逻辑 ...
        Object o = p.proceed();
        // 执行后逻辑 ...
        return o;
    }
}
```

### 执行顺序

执行顺序和切面类的字典序有关，对于 Before 来说，字典类靠前的先执行，After 相反

@Order 注解在类上

```java
@Order(4) // 对于@Before越小越先执行，@After相反
class Demo{
    @Around("pt()")
    public Object Timer(ProceedingJoinPoint p)
            throws Throwable{
        // 执行前逻辑 ...
        Object o = p.proceed();
        // 执行后逻辑 ...
        return o;
    }
}
```

## SpringBean 中的注解

@Scale

- 默认单例，应用启动时创建，可以用@Lazy 延迟初始化
- @Scale("prototype") 非单例

## SpringBean 中的条件注解

比如存在"com.jacky.Student"才会加入 IOC 容器中

@ConditionalOnClass(name="com.jacky.Student")

---

不存在该类型的 bean，才会加入到容器中

@ConditionalOnMissingBean

用途：一般是用户自定义 Bean，就不加载默认的 Bean 了

---

@ConditionalOnProperty(name="name",havingValue="jacky")

判断配置文件中是否有对应的值，有就加载 Bean 到 IOC 容器中

用途：在 Spring 中整合第三方库的时候，只有在配置中声明了对应的 Bean 对象

## 参考资料

- [Spring 高级之注解@PropertySource 详解（超详细）\_propetysource-CSDN 博客](https://blog.csdn.net/qq_40837310/article/details/106587158)
