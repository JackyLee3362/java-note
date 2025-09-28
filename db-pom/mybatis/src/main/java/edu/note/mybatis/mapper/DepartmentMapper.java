package edu.note.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import edu.note.mybatis.model.Department;

/**
 * @author jackylee
 * @date 2025/9/25 22:13
 */
public interface DepartmentMapper {

    @Select("SELECT * FROM dept WHERE id = #{id}")
    Department selectById(Integer id);

}
