package edu.note.spring.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025-10-11 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private Integer age;

}
