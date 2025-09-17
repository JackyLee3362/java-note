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

## Controller中的注解

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

接收json格式的参数

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

query参数

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

1. 首先必须yml中的配置名与类中的变量名保持一致
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

位于Service层上 / 类 / 接口

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
- @After  : 后置通知，无论有异常都会被执行
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

执行顺序和切面类的字典序有关，对于Before来说，字典类靠前的先执行，After相反

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

## SpringBean中的注解

@Scale

- 默认单例，应用启动时创建，可以用@Lazy 延迟初始化
- @Scale("prototype") 非单例

## SpringBean中的条件注解

比如存在"com.jacky.Student"才会加入IOC容器中

@ConditionalOnClass(name="com.jacky.Student")

---

不存在该类型的bean，才会加入到容器中

@ConditionalOnMissingBean

用途：一般是用户自定义Bean，就不加载默认的Bean了

---

@ConditionalOnProperty(name="name",havingValue="jacky")

判断配置文件中是否有对应的值，有就加载Bean到IOC容器中

用途：在Spring中整合第三方库的时候，只有在配置中声明了对应的Bean对象

## 参考资料

- [Spring高级之注解@PropertySource详解（超详细）_propetysource-CSDN博客](https://blog.csdn.net/qq_40837310/article/details/106587158)
