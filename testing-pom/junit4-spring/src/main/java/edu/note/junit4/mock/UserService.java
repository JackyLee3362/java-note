package edu.note.junit4.mock;

import org.springframework.stereotype.Service;

/**
 * @author jackylee
 * @date 2025-10-09 16:11
 */
@Service
public class UserService {

    private UserRepository userRepo;

    public String getUser(String username) {
        if (userRepo.getByUserName(username)) {
            return username.toUpperCase();
        }
        return null;
    }

}
