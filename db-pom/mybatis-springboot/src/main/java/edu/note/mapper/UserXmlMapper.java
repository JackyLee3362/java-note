package edu.note.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.note.domain.MyUser;

/**
 * @author jackylee
 * @date 2025-12-09 10:18
 */
@Mapper
public interface UserXmlMapper {

    MyUser selectById(Integer id);

    int insertUser(MyUser user);
}
