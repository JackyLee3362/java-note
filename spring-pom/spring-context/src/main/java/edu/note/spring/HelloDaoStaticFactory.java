package edu.note.spring;

/**
 * @author jackylee
 * @date 2025-09-30 15:01
 */
public class HelloDaoStaticFactory {

    public static HelloDao getHelloDao() {
        return new HelloDaoImpl();
    }

}
