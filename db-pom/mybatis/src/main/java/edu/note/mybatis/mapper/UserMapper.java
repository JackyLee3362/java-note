package edu.note.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import edu.note.mybatis.model.User;

/**
 * @author jackylee
 * @date 2025/9/25 11:59
 */
public interface UserMapper {
    User selectById(int id);

    @Select("SELECT * FROM user WHERE name = #{name}")
    User selectByName(String name);

    List<User> list();

    @Select("SELECT * FROM user;")
    List<User> listByAnno();

    Integer insertUser(User user);

    Integer insertUserByParam(@Param("name") String name, @Param("city") String city);

    Integer insertBatch(List<User> users);

    void updateUser(User user);

    void deleteUser(Integer id);

}
