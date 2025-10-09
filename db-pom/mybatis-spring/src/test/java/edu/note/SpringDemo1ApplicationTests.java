package edu.note;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringDemo1ApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Test
    public void testListUser() {
        List<User> userList = userMapper.list();
        userList.forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        int deleteNumber = userMapper.deleteUser(2998);
        System.out.println(deleteNumber);
    }

    @Test
    public void testInsert() {
        User user = new User(2, "Jie", 30);
        userMapper.insertUser(user);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate() {
        User user = new User(3003, "Jones", 100);
        userMapper.updateUser(user);
    }

    @Test
    public void testSelect() {
        User user = userMapper.getById(3003);
        System.out.println(user);
    }

    @Test
    public void testSelectUsers() {
        List<User> list = userMapper.filterByAge("a", 100, 200);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelect2() {
        User user = userMapper.getById(3003);
        System.out.println(user);
    }
}
