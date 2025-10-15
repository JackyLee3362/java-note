package edu.note.spring.bean.xml;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author jackylee
 * @date 2025-09-30 15:13
 */
public class HelloDaoFactoryBean implements FactoryBean<HelloDao> {

    @Override
    public HelloDao getObject() throws Exception {
        return new HelloDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return HelloDao.class;
    }

    // @Override
    // public boolean isSingleton() {
    //     return true;
    // }

}
