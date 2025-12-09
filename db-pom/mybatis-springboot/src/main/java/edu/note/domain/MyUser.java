package edu.note.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    private Integer id;
    private String name;
    private Integer age;

}
