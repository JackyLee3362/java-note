package edu.note.spring;

/**
 * @author jackylee
 * @date 2025-09-30 13:52
 */
public class HelloServiceImpl implements HelloService {

    private HelloDao helloDao;

    // 即使是 private 也能创建成功，说明 spring 底层是反射
    private HelloServiceImpl() {
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
