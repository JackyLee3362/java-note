---
title: groovy 基础
tags:
  - 编程
  - groovy/基础
create_time: 2024-10-08
---

## 压制可见性警告

```java
@SuppressWarnings("GroovyAccessibility")
```

## 依赖

```xml
<!-- 1.Spock 相关 -->
<dependency>
    <groupId>org.spockframework</groupId>
    <artifactId>spock-core</artifactId>
    <version>1.3-groovy-2.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.spockframework</groupId>
    <artifactId>spock-spring</artifactId>
    <version>1.3-groovy-2.4</version>
    <scope>test</scope>
</dependency>
<!-- 2.PowerMock 相关-->
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
    <version>2.0.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito2</artifactId>
    <version>2.0.4</version>
    <scope>test</scope>
</dependency>
```

## 单测 Demo

### 静态类单测 Demo

```groovy
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class) // 交给 spock 代理执行
@PrepareForTest([静态类.class])
@SuppressStaticInitializationFor(["静态类全名"]) // 限制静态类里的静态代码块初始化
class StaticMethodSpec extends Specification {

    def service = Mock(UserService)

    def controller = new UserController(userService: service)

    void setup() {
        // Mock静态类
        PowerMockito.mockStatic(静态类.class)
    }

    @Unroll
    def "测试类名 #desc"() {
        given: "给定条件"
        def user = service.getById(_ as Integer) >> [new User(name: "Alice", age: 12), null]
        def map = [k1:v1, k2:v2]
        def list = [1,2,3]

        and: "mock静态方法返回值"
        PowerMockito.when(静态类.静态方法(Mockito.any())).thenReturn(自己想要的返回值)

        when: "调用时"
        def res = controller.getById(v_id)


        then: "比较"
        res != exp
        // 或者 
        with(res){
            name == v_name
            age == v_age
        }

        where "数据格式"
        desc      | v_id || exp
        "success" | 1    || new User(name: "Alice", age:12)
        "fail"    | -1   || null
    }
}
```

### 捕获异常

```groovy
def "测试类"(){
    //...

    then:
    def exception = thrown(v_expectException)
    exception.code == v_expectCode
    exception.message == v_expectMessage

    // ...
}
```

### 校验参数

```groovy
def "测试类"(){
    //...

    then:
    1 * 某类.某方法({
        it.某属性1 == 属性1
        it.某属性2 == 属性2
    })

    // ...
}
```

## 参考文献

- [6. Test Guide — groovys 1.0.0 文档](https://groovys.readthedocs.io/zh/latest/GettingStarted/Testing-guide.html)
- [另类 Springboot 集成单元测试：Groovy 脚本测试、零重启、零等待 🗣️ 什么是 Springboot 集成 - 掘金](https://juejin.cn/post/7208085491244793916)
