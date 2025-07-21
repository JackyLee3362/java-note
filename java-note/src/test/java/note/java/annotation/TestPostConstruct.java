package note.java.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/7/15 14:43
 */
public class TestPostConstruct {

    @Test
    public void testPostConstruct() {
        User user = new User();
        // TODO 是不是只能配合 Spring 完成
        Assertions.assertEquals("default", user.getName());
    }

}
