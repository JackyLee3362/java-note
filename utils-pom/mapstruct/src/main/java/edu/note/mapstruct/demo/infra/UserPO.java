package edu.note.mapstruct.demo.infra;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户持久层对象
 *
 * @author jackylee
 * @date 2025/7/1 17:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    private Long id;

    private String firstName;

    private String lastName;

    // 转换时需要校验
    private String email;

    private Integer userType;

    private LocalDate birthday;

    private LocalDateTime registerTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
