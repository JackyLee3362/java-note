package edu.note.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/9/25 13:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;

    private String name;

    private String password;
}
