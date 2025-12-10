package edu.note.spring.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class LeetcodeTest {
    public final static String QUERY_TEMPLATE = "query questionData($titleSlug: String!) \n" +
            "    {\n" +
            "        question(titleSlug: $titleSlug) \n" +
            "        {\n" +
            "                questionFrontendId\n" +
            "                translatedTitle\n" +
            "                translatedContent\n" +
            "                difficulty\n" +
            "                langToValidPlayground\n" +
            "                topicTags\n" +
            "                {   name\n" +
            "                    slug\n" +
            "                    translatedName\n" +
            "                }\n" +
            "                codeSnippets\n" +
            "                {\n" +
            "                    lang\n" +
            "                    langSlug\n" +
            "                    code\n" +
            "                }\n" +
            "                hints \n" +
            "                sampleTestCase\n" +
            "                metaData\n" +
            "        }\n" +
            "    }";
    @Autowired
    public RestTemplate restTemplate;

    @Test
    public void getEveryProblemTitle() throws JsonProcessingException {
        // 1 查看是否注入成功
        System.out.println(restTemplate);
        // 2 构造请求
        // 2.1 构造头部
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNodes = objectMapper.createObjectNode();
        jsonNodes.put("query", "query questionOfToday{ todayRecord {question { questionTitleSlug } } }");
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonNodes.toString(), headers);
        String url = "https://leetcode.cn/graphql";
        // 3 发送请求
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        System.out.println(response);
        // 4 解析头部
        String body = response.getBody();
        System.out.println(body);
        // json解析
        JsonNode rootNode = objectMapper.readTree(body);
        JsonNode questionNode = rootNode.path("data").path("todayRecord").get(0).path("question");
        System.out.println(questionNode.path("questionTitleSlug").asText());
    }

    @Test
    public void getProblemInfo() throws JsonProcessingException {
        String title = "find-maximum-number-of-string-pairs";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ObjectNode jsonNodes = objectMapper.createObjectNode();
        jsonNodes.put("operationName", "questionData");
        jsonNodes.put("variables", String.format("{\"titleSlug\": \"%s\"}", title));
        jsonNodes.put("query", QUERY_TEMPLATE);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonNodes.toString(), headers);
        String url = "https://leetcode.cn/graphql";
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        // 解析
        String body = response.getBody();
        JsonNode rootNode = objectMapper.readTree(body);
        JsonNode questionNode = rootNode.path("data").path("question");
        questionNode.get("topicTags");
        String problemString = questionNode.toString();
        Problem problem1 = objectMapper.treeToValue(questionNode, Problem.class);
        System.out.println(problem1);
        Problem problem = objectMapper.readValue(problemString, Problem.class);
        System.out.println(problem);
    }
    @Data
    // @NoArgsConstructor
    static public class Problem {
        private String questionFrontendId;
        private String translatedTitle;
        private String translatedContent;
        private String difficulty;
        private String langToValidPlayground;
        // private List<String> topicTags;
        // private List<String> codeSnippets;
        // private List<String> hints;
        private String sampleTestCase;
        private String metaData;

    }
}
