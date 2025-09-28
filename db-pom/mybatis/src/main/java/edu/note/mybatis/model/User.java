package edu.note.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/9/25 11:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    Integer id;
    String name;
    Integer age;

}
