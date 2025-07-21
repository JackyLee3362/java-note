package note.java.annotation;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025/7/15 14:44
 */
@NoArgsConstructor
public class User {

    @Getter
    private String name;

    @PostConstruct
    public void init() {
        this.name = "default";
    }


}
