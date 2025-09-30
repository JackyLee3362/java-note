package edu.note.spring;

/**
 * @author jackylee
 * @date 2025-09-30 15:07
 */
public class HelloDaoFactory {

    public HelloDao getHelloDao() {
        return new HelloDaoImpl();
    }
}
