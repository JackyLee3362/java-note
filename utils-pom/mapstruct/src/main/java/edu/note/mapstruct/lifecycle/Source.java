package edu.note.mapstruct.lifecycle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

}
