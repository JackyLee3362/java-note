package edu.note.spock;

import lombok.Data;

/**
 * @author jackylee
 * @date 2025/7/1 14:29
 */
@Data
public class UserServiceImpl implements UserService {

    private static final UserDao staticuserDao = null;

    private UserDao userDao;

    public UserServiceImpl() {

    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String getServiceInfo() {
        // 不包含任何依赖
        return "UserService V1.0";
    }

    @Override
    public User getUserInfo(Integer idNumber) {
        // 包含依赖
        return userDao.selectById(idNumber);
    }

    @Override
    public Boolean isOddAgeUser(Integer idNumber) {
        // 包含静态方法
        User user = getUserInfo(idNumber);
        return Calculator.isOdd(user.getAge());
    }


}
