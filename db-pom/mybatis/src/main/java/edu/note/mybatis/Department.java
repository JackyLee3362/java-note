package edu.note.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/9/25 22:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    /**
     * id
     */
    Integer id;

    /**
     * 部门名称
     */
    String name;


}
