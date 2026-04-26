package edu.note.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.note.domain.User;

/**
 * @author jackylee
 * @date 2025-12-09 10:18
 */
@Mapper
public interface UserXmlMapper {

    User selectById(Integer id);

    int insertUser(User user);
}
