package note.mapstruct;

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
public class UserPO {

    private Long id;
    private String name;
    private Integer age;

}
