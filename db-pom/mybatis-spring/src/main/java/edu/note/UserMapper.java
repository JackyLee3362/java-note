package edu.note;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM person LIMIT 10")
    List<User> list();

    @Delete("DELETE FROM person WHERE id = #{id}")
    int deleteUser(Integer id); // 一般用void，int返回被影响的行数

    // 会自动生成主键值，然后赋给user对象的id属性
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO person(name, age) VALUES (#{name}, #{age})")
    void insertUser(User user);

    @Update("UPDATE person SET name=#{name}, age=#{age} WHERE id = #{id}")
    void updateUser(User user);

    @Select("SELECT id, name, age FROM person WHERE id = #{id}")
    User getById(Integer id);
    // 如果数据库中的字段名是user_name，但是在User类中是userName，
    // Mybatis没法封装这个数据，我们有两种解决方式
    // 第一种方式：起别名
    // 我们可以在SELECT中起别名
    // SELECT id, user_name as userName, age FROM ...
    // 第二种方式：添加注解手动映射
    // @Results({
    // @Result(column = "user_name", property = "userName"),
    // @Result(column = "user_age", property = "userAge"),
    // })// 另一种方式是添加注解
    // 第三种方式：开启mybatis的自动驼峰命名开关
    // application.properties中
    // mybatis.configuration.map-underscore-to-camel-case=true

    // @Select("SELECT * FROM person WHERE name LIKE '${name}%' AND age BETWEEN
    // #{minAge} AND #{maxAge}")
    @Select("SELECT * FROM person WHERE name LIKE concat(#{name}, '%') AND age BETWEEN #{minAge} AND #{maxAge}")
    List<User> filterByAge(String name, Integer minAge, Integer maxAge);
    // 这里不能用'#{name}%'，因为预编译后会变成'?%'，但是?是不能出现在引号里的
    // SELECT * FROM person WHERE name LIKE concat(#{name}, '%') ...

    User getByIdXML(Integer id);
}
