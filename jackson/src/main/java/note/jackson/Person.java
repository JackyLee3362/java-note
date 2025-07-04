package note.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/1 17:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    private String name;
    private int age;
}
