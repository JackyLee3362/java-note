package edu.note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025-12-08 19:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    private Long id;
    private String name;
    private Integer age;

}
