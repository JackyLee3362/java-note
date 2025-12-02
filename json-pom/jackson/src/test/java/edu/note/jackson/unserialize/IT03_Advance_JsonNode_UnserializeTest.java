package edu.note.jackson.unserialize;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jackylee
 * @date 2025-11-28 15:03
 */
public class IT03_Advance_JsonNode_UnserializeTest {

    static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setup() {
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
    }

    @Test
    @DisplayName("JsonNode -> 对象")
    void test01() throws JsonMappingException, JsonProcessingException {
        String string = "{'foo': 'bar'}";
        JsonNode node = objectMapper.readTree(string);
        System.out.println(node);

    }

}
