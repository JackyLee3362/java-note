package edu.note;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.domain.User;
import edu.note.mapper.UserXmlMapper;

/**
 * @author jackylee
 * @date 2026-01-19 18:56
 */
public class UserXmlMapperTest {

    @Resource
    private UserXmlMapper userMapper;

    @Test
    @DisplayName("测试")
    void test01() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
