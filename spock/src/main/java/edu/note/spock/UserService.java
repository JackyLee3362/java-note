package edu.note.spock;

/**
 * @author jackylee
 * @date 2025/7/1 14:29
 */
public class UserService {

    private static final UserDao staticuserDao = null;
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserPO getUserInfo(Integer idNumber) {
        return userDao.getUserById(idNumber);
    }

    public Boolean isOddAgeUser(Integer idNumber) {
        UserPO user = getUserInfo(idNumber);
        return NumberUtil.isOdd(user.getAge());
    }


}
