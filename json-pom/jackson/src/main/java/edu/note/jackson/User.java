package edu.note.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String name;
    private Integer age;
}
