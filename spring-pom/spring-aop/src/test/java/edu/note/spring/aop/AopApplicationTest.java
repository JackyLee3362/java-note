package edu.note.spring.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AopApplicationTest {

    private String baseUrl;

    // @LocalServerPort
    @Value("${local.server.port}")
    private int port;

    RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
        restTemplate = new RestTemplate();
    }

    @Test
    void testUserGetById() {
        String url = baseUrl + "/user/get?id=1";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }

    @Test
    void testUserList() {
        String url = baseUrl + "/user/list";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }

    @Test
    void testOrderControllerGetById() {
        String url = baseUrl + "/order/get?id=1";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }

    @Test
    void testOrderControllerList() {
        String url = baseUrl + "/order/list";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
    }

}
