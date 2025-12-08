package edu.note.mapstruct.fieldmapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

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
    private String email;
    private Integer age;
    private LocalDate birthday;
    private LocalDateTime createTime;

}
