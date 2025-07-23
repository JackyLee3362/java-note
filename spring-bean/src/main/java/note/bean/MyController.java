package note.bean;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author jackylee
 * @date 2025/7/23 17:05
 */
@Service
public class MyController {

    @Bean
    public User admin() {
        User myBean = new User();
        myBean.setName("Alice");
        myBean.setAge(18);
        return myBean;
    }

    @Data
    static public class User {
        private String name;
        private int age;
    }

}
