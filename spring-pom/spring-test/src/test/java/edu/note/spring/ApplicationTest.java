package edu.note.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.note.spring.flow.Flow;
import edu.note.spring.flow.FlowExecutor;
import edu.note.spring.service.UserService;

/**
 * @author jackylee
 * @date 2025-10-14 20:19
 */
@SpringBootTest
public class ApplicationTest {

    @Resource
    private UserService userService;

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    @DisplayName("测试注入")
    void test01() {
        assertNotNull(userService);
    }

    @Test
    @DisplayName("测试注入列表")
    void test02() {
        List<Flow> flowList = flowExecutor.getFlowList();
        assertEquals(2, flowList.size());
    }

}
