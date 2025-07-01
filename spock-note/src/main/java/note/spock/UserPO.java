package note.spock;

import java.io.Serializable;
import java.util.LinkedHashMap;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jackylee
 * @date 2025/7/1 14:51
 */
public class UserPO {

    Integer id;
    String name;
    Integer age;

    public UserPO() {
    }

    public UserPO(Integer id, String name, Integer age) {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

}
