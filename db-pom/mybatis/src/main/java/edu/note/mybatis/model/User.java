package edu.note.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/9/25 11:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    Integer id;

    String name;

    String city;

}
