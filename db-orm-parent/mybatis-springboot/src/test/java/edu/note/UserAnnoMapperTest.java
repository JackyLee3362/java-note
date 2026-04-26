package edu.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.note.domain.User;
import edu.note.mapper.UserAnnoMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

@SpringBootTest(classes = StartApp.class)
class UserAnnoMapperTest {

    @Resource
    public UserAnnoMapper userAnnoMapper;

    @Test
    @DisplayName("测试查找 User 列表")
    public void testListUser() {
        List<User> userList = userAnnoMapper.list();
        assertNotNull(userList);
    }

    @Test
    @DisplayName("测试删除 User")
    public void testDelete() {
        int deleteNumber = userAnnoMapper.deleteUser(2998);
        assertEquals(deleteNumber, 0);
    }

    @Test
    @DisplayName("测试插入 User")
    public void testInsert() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(2, "Foo", 30, now, false);
        userAnnoMapper.insertUser(user);
        System.out.println(user.getId());
    }

    @Test
    @DisplayName("测试更新数据 User")
    public void testUpdate() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(3003, "Jones", 100, now, true);
        userAnnoMapper.updateUser(user);
    }

    @Test
    @DisplayName("测试查找数据 User")
    public void testSelect() {
        User user = userAnnoMapper.selectById(3003);
        System.out.println(user);
    }

    @Test
    @DisplayName("测试查找数据 User")
    public void testSelectUsers() {
        List<User> list = userAnnoMapper.filterByAge("a", 100, 200);
    }

    @Test
    @DisplayName("测试查找数据 By 姓名")
    public void testSelect2() {
        User user = userAnnoMapper.selectById(3003);
    }
}
