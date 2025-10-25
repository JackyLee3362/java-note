package edu.note.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025-10-14 16:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private Integer age;
    private Address address;

}
