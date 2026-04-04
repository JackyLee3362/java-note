package edu.note.mapstruct.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import edu.note.mapstruct.demo.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户领域对象
 *
 * @author jackylee
 * @date 2025/7/1 17:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;

    private String name;

    private String email;

    private UserType userType;

    private List<Address> addresses;

    private LocalDate birthday;

    private LocalDateTime registerTime;

}
