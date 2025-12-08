package edu.note.mapstruct.fieldmapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Target {

    private Long userId;
    private String name;
    private Integer age;
    private String address;
    private LocalDate birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
