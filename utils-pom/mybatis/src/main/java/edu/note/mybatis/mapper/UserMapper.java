package edu.note.mybatis.mapper;

import edu.note.mybatis.User;

/**
 * @author jackylee
 * @date 2025/9/25 11:59
 */
public interface UserMapper {
    User selectById(int id);

    void insertUser(User user);
}
