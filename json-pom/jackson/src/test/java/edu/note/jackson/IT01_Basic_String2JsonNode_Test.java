package edu.note.jackson;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author jackylee
 * @date 2025-11-27 17:39
 */
public class IT01_Basic_String2JsonNode_Test {

    static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("从 json 获得 JsonNode 对象（树模型）")
    void testJsonDemo4() throws IOException {
        // 树模型
        String studentJson = "{\"data\":{\"name\":\"Mike\", \"age\":18}}";
        JsonNode rootNode = objectMapper.readTree(studentJson);
        JsonNode jsonNode = rootNode.get("data");
        String string = jsonNode.toString();
        System.out.println(string);
    }

    @Test
    @DisplayName("从 JsonNode 构造 json")
    void testJsonDemo5() {
        ObjectNode jsonNodes = objectMapper.createObjectNode();
        jsonNodes.put("name", "Mike");
        jsonNodes.put("age", 18);
        String string = jsonNodes.toString();
        System.out.println(string);
    }

}
