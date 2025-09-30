---
type: basic-note
title: 2023-05-23-Mybatis框架
author: JackyLee
create_date: 2023-05-23
update_date:
tags:
description:
---



## 参考资料

# Mybatis

application.yml配置

```yml
server:
    port: 8080

spring:
    datasource:
            url: jdbc:mysql://localhost:3306/test
            username: root
            password: jackylee1997
            driver-class-name: com.mysql.cj.jdbc.Driver
# 查看mybatis日志
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

```java
// pojo -- 存储对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
    // ... Getter/Setter/Constructor
}
// mapper.UserMapper
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM person LIMIT 10")
    public List<User> list();

    @Delete("DELETE FROM person WHERE id = #{id}")
    public int deleteUser(Integer id); // 一般用void，int返回被影响的行数

    // 会自动生成主键值，然后赋给user对象的id属性
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO person(name, age) VALUES (#{name}, #{age})")
    public void insertUser(User user);

    @Update("UPDATE person SET name=#{name}, age=#{age} WHERE id = #{id}")
    public void updateUser(User user);

    @Select("SELECT id, name, age FROM person WHERE id = #{id}")
    public User getById(Integer id);
    // 如果数据库中的字段名是user_name，但是在User类中是userName，
    // Mybatis没法封装这个数据，我们有两种解决方式
    // 第一种方式：起别名
    // 我们可以在SELECT中起别名
    // SELECT id, user_name as userName, age FROM ...
    // 第二种方式：添加注解手动映射
    //    @Results({
    //            @Result(column = "user_name", property = "userName"),
    //            @Result(column = "user_age", property = "userAge"),
    //    })// 另一种方式是添加注解
    // 第三种方式：开启mybatis的自动驼峰命名开关
    // application.properties中
    // mybatis.configuration.map-underscore-to-camel-case=true

    //  @Select("SELECT * FROM person WHERE name LIKE '${name}%' AND age BETWEEN #{minAge} AND #{maxAge}")
    @Select("SELECT * FROM person WHERE name LIKE concat(#{name}, '%') AND age BETWEEN #{minAge} AND #{maxAge}")
    public List<User> filterByAge(String name, Integer minAge, Integer maxAge);
    // 这里不能用'#{name}%'，因为预编译后会变成'?%'，但是?是不能出现在引号里的
    // SELECT * FROM person WHERE name LIKE concat(#{name}, '%') ...
}
// test测试代码
@SpringBootTest
class MybatisDemoApplicationTests {

    @Autowired
    public UserMapper userMapper;
    @Test
    void testListUser(){
        List<User> userList = userMapper.list();
        userList.stream().forEach(System.out::println);
    }

    @Test
    void testDelete(){
        int deleteNumber = userMapper.deleteUser(2998);
        System.out.println(deleteNumber);
    }

    @Test
    void testInsert(){
        User user = new User(2, "Jie", 30);
        userMapper.insertUser(user);
        System.out.println(user.getId());
    }

    @Test
    void testUpdate(){
        User user = new User(3003, "Jone", 100);
        userMapper.updateUser(user);
    }

    @Test
    void testSelect(){
        User user = userMapper.getById(3003);
        System.out.println(user);
    }
    
    @Test
    void testSelectUsers(){
        List<User> list = userMapper.filterByAge("a", 100, 200);
        list.stream().forEach(System.out::println);
    }
}
```

## 使用配置文件

- XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）
- XML映射文件的namespace属性为Mappper接口全限定名一致
- XML映射文件中的SQL语句的id与Mapper接口中的方法名一致，并保持返回类型一致

## 单独开发Mybatis配置文件

SqlMapConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbc.properties"></properties>
    <typeAliases>
        <package name="com.example.domain"/>
    </typeAliases>
    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <package name="com.example.dao"/>
    </mappers>
</configuration>
```

jdbc.properties

```conf
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC
jdbc.username=root
jdbc.password=jackylee1997
```

主函数

```java
public class AppForAnnotation {
    public static void main(String[] args) throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        UserDao userDao;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);

        System.out.println(userDao);
        System.out.println(userDao.getClass());
        userDao.findAll();
        sqlSession.close();
    }
}
```

## Spring集合Mybatis

SpringConfig

```java
@Configuration
@ComponentScan("com.example")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class}) // 主要是加MybatisConfig.class
public class SpringConfig {
}
```

MybatisConfig.java（主要是加这个配置类，固定格式）

```java
public class MybatisConfig {
    // 主要是扫描类型别名的包
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.example.domain");
        ssfb.setDataSource(dataSource);
        return ssfb;
    }

    // 扫描bean设的包
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.example.dao");
        return msc;
    }
}
```

主函数

```java
public class AppMybatisSpring {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = ctx.getBean(UserService.class);

        userService.save();
        List<User> userList = userService.findAll();
        userList.forEach(System.out::println);
    }
}
```
