package edu.note;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jackylee
 * @date 2026-01-14 10:53
 */
@SpringBootTest // (classes = StartApp.class)
public class SpringListInjectionTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    @DisplayName("测试注入列表")
    void test01() {
        List<Flow> flowList = flowExecutor.getFlowList();
        assertEquals(2, flowList.size());
    }

}
