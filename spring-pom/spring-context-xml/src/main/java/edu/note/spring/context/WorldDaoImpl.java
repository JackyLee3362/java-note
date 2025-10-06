package edu.note.spring.context;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author jackylee
 * @date 2025-09-30 16:16
 */
public class WorldDaoImpl implements WorldDao, InitializingBean, DisposableBean {
    
    int maxCount;

    @Override
    public void destroy() throws Exception {
        System.out.println("destory...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 其实不是 new 时执行，是在属性 set 后执行
        System.out.println("init...");
    }

}
