package edu.note.mapper;

import org.apache.ibatis.annotations.*;

import edu.note.domain.User;

import java.util.List;

@Mapper
public interface UserAnnoMapper {

    // 如果数据库中的字段名是user_name，但是在User类中是userName，
    // Mybatis没法封装这个数据，我们有3种解决方式
    // 1. 起别名: SELECT id, user_name as userName, age FROM ...
    // 2. 添加注解手动映射 @Results({@Result(column = "user_name", property = "userName")})
    // 3. 配置文件开启 mybatis 的自动驼峰命名开关(推荐):
    // mybatis.configuration.map-underscore-to-camel-case=true
    @Select("SELECT id, name, age FROM user WHERE id = #{id}")
    User selectById(Integer id);

    @Select("SELECT * FROM user LIMIT 10")
    List<User> list();

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(Integer id);

    // 会自动生成主键值，然后赋给user对象的id属性
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("INSERT INTO user(name, age) VALUES (#{name}, #{age})")
    void insertUser(User user);

    @Update("UPDATE user SET name=#{name}, age=#{age} WHERE id = #{id}")
    void updateUser(User user);


    // @Select("SELECT * FROM user WHERE name LIKE '${name}%' AND age BETWEEN
    // #{minAge} AND #{maxAge}")
    @Select("SELECT * FROM user WHERE name LIKE concat(#{name}, '%') AND age BETWEEN #{minAge} AND #{maxAge}")
    List<User> filterByAge(String name, Integer minAge, Integer maxAge);
    // 这里不能用'#{name}%'，因为预编译后会变成'?%'，但是?是不能出现在引号里的
    // SELECT * FROM user WHERE name LIKE concat(#{name}, '%') ...
}
