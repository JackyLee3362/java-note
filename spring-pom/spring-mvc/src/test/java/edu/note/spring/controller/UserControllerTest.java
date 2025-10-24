package edu.note.spring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.note.spring.User;

/**
 * @author jackylee
 * @date 2025-10-14 16:50
 */
@WebMvcTest(UserRestController.class)
public class UserControllerTest {

    @Resource
    private MockMvc mockMvc;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("测试基础")
    void testGet() throws Exception {
        MockHttpServletRequestBuilder request = get("/user/list");
        MvcResult mockResult = mockMvc.perform(request).andReturn();
        String string = mockResult.getResponse().getContentAsString();
        List<User> user = objectMapper.readValue(string, new TypeReference<List<User>>() {
        });
        assertEquals(1, user.get(0).getId());
    }

    @Test
    @DisplayName("测试 RequestParam")
    void testRequestParam() throws Exception {
        MockHttpServletRequestBuilder request = get("/user/info").param("id", "12");
        MvcResult mockResult = mockMvc.perform(request).andReturn();
        String string = mockResult.getResponse().getContentAsString();
        User user = objectMapper.readValue(string, User.class);
        assertEquals(12, user.getId());
    }

    @Test
    @DisplayName("测试 RequestBody")
    void testRequestBody() throws Exception {
        User newUser = new User(1, "Foo", 100);
        MockHttpServletRequestBuilder request = post("/user/insert").content(objectMapper.writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mockResult = mockMvc.perform(request).andReturn();
        // 获取结果
        String string = mockResult.getResponse().getContentAsString();
        User user = objectMapper.readValue(string, User.class);
        assertEquals(newUser, user);
    }

    @Test
    @DisplayName("测试 PathVariable")
    void testPathVariable() throws Exception {
        MockHttpServletRequestBuilder request = delete("/user/delete/1");
        MvcResult mockResult = mockMvc.perform(request).andReturn();
        // 获取结果
        String string = mockResult.getResponse().getContentAsString();
        Boolean bool = objectMapper.readValue(string, Boolean.class);
        assertTrue(bool);
    }

    @Test
    @DisplayName("测试 RequestHeader")
    void testRequestHeader() throws Exception {
        MockHttpServletRequestBuilder request = get("/user/header")
                .header("Accept-Encoding", "gzip, deflate");
        MvcResult mockResult = mockMvc.perform(request).andReturn();
        // 获取结果
        String string = mockResult.getResponse().getContentAsString();
        Boolean bool = objectMapper.readValue(string, Boolean.class);
        assertTrue(bool);
    }

}
