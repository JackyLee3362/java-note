package edu.note.spring.context;

/**
 * @author jackylee
 * @date 2025-09-30 13:52
 */
public class HelloServiceImpl implements HelloService {

    private HelloDao helloDao;
    private String prefix;

    // 即使是 private 也能创建成功，说明 spring 底层是反射
    private HelloServiceImpl() {
    }
    
    // 构造器注入
    public HelloServiceImpl(String prefix, HelloDao helloDao){
        this.helloDao = helloDao;
        this.prefix = prefix;
    }

    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

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
