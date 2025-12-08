package edu.note;

import org.springframework.stereotype.Service;

import edu.note.entity.UserPO;

/**
 * @author jackylee
 * @date 2025-12-08 19:32
 */
public class UserServiceImpl {

    public void printUser(UserPO user) {
        System.out.println("User Info: " + user);
    }

}
