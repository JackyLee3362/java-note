package edu.note.junit4.mock;

import org.springframework.stereotype.Repository;

/**
 * @author jackylee
 * @date 2025-10-09 16:11
 */
@Repository
public class UserRepository {

    public Boolean getByUserName(String username) {
        return true;
    }
}
