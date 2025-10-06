package edu.note.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author jackylee
 * @date 2025-10-05 15:57
 */
@Component
public class BookDaoImpl implements BookDao {

    @Override
    public int save() {
        System.out.println("bookDao saving...");
        return 0;
    }

    @Override
    public void update() {
        System.out.println("bookDao updating...");
    }

}
