package edu.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.note.entity.UserPO;

/**
 * @author jackylee
 * @date 2025-12-08 19:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public void printUser(UserPO user) {
        System.out.println("User Info: " + user);
    }

    @Override
    public UserPO getUserById(Long id) {
        UserPO selectById = userMapper.selectById(id);
        return selectById;
    }

}
