package edu.note.generate.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements Serializable {
    private Long id;

    private String name;

    private Integer age;

    private static final long serialVersionUID = 1L;
}