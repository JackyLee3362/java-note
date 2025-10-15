package edu.note.spring.bean.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author jackylee
 * @date 2025-10-05 12:32
 */
@Data
@Component
// @Scope("prototype")
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloDao helloDao;

    @Override
    public String hello(String name) {
        helloDao.save(name);
        return "Hello, " + name;
    }

    @Override
    public String bye() {
        return "bye";
    }

}
