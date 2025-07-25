package edu.note.spring.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestTemplateApplicationTest {
    // 测试前无需启动服务

    @LocalServerPort
    private int port;

    String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port;
        System.out.println("baseURL是"+baseUrl);
    }

    @Test
    public void testRestTemplate() {
        String url = baseUrl + "/hello?id=1";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }
}