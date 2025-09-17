package edu.note.spock;

/**
 * @author jackylee
 * @date 2025/7/1 14:43
 */
public interface UserDao {

    // 即使不加 static final 修饰，接口中的变量默认也是静态、final 的
    static final UserPO ADMIN = new UserPO(0, "Admin", 0);

    UserPO getUserById(Integer id);


}
