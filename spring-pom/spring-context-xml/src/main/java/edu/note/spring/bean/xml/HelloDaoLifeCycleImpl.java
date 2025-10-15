package edu.note.spring.bean.xml;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * @author jackylee
 * @date 2025-10-05 14:58
 */
@Component
public class HelloDaoLifeCycleImpl implements HelloDao {

    @Override
    public Boolean save(String name) {
        System.out.println("HelloDaoImpl saving...");
        return true;
    }

    @PostConstruct //在构造方法之后执行，替换 init-method
    public void init() {
        System.out.println("init ...");
    }
    @PreDestroy //在销毁方法之前执行,替换 destroy-method
    public void destroy() {
        System.out.println("destroy ...");
    }

}