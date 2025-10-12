package edu.note.spring.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jackylee
 * @date 2025-10-11 20:15
 */
// 指示Spring Boot在随机且可用的端口上启动Web服务器，避免端口冲突
// @SpringBootTest(classes = StartApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
// 自动配置MockMvc和Spring IOC容器
// @AutoConfigureMockMvc
// @WebAppConfiguration
// @AutoConfigureMockMvc
public class UserControllerTest {

    MockMvc mockMvc;

    // 初始化MockMvc
    @BeforeEach
    void setUp(WebApplicationContext wac) {
        // 方式1：明确指定需要测试的“Controller”类
        mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController()).build();

        // 方式2：基于Spring容器进行配置，包含了Spring MVC环境和所有“Controller”类。
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // 访问GET接口：不带参数
    @Test
    public void testSimpleGet() throws Exception {
        // int id = 10;
        // 方式1：在URI模板中指定参数
        // MockHttpServletRequestBuilder requestBuilder =
        // MockMvcRequestBuilders.get("/test?id={id}", id);

        // 方式2：通过param()方法指定参数
        // MockHttpServletRequestBuilder requestBuilder =
        // MockMvcRequestBuilders.get("/test").param("id", String.valueOf(id));

        // 方式3：通过queryParam()方法指定参数
        // MockHttpServletRequestBuilder requestBuilder =
        // MockMvcRequestBuilders.get("/test").queryParam("id", String.valueOf(id));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/list")
                // 接受JSON格式响应消息
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                // 检查响应状态码是否为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 获取返回结果
                .andReturn();
        Assertions.assertEquals("mock users list", result.getResponse().getContentAsString());
    }
}
