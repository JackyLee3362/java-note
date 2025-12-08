package edu.note.mapstruct.lifecycle;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Target {

    private Long userId;
    private String name;
    private Integer age;
    private LocalDateTime createTime;

}
