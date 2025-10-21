package edu.note.spring.bean.anno;

import org.springframework.stereotype.Repository;

/**
 * @author jackylee
 * @date 2025-09-30 13:55
 */
// @Component
@Repository
public class HelloDaoImpl implements HelloDao {

    @Override
    public Boolean save(String name) {
        System.out.println("HelloDao Impl saving..." + name);
        return true;
    }

    // 表示bean初始化对应的操作
    public void init() {
        System.out.println("init...");
    }

    // 表示bean销毁前对应的操作
    public void destory() {
        System.out.println("destory...");
    }
}
