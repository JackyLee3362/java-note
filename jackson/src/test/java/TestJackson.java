import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.LocalDateTime;
import edu.note.jackson.Student;
import edu.note.jackson.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// https://juejin.cn/post/6844904166809157639
public class TestJackson {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("反序列化：从 json 获得 java 对象")
    public void testJsonDemo() throws IOException {
        String studentJson = "{\"name\":\"Mike\", \"age\":18}";
        Student student = objectMapper.readValue(studentJson, Student.class);
        System.out.println(student);
    }

    @Test
    @DisplayName("序列化：从 java 对象 获得 json")
    public void testJsonDemo2() throws JsonProcessingException {
        Student student = new Student("Mike", 12, 130, null, null);
        String s = objectMapper.writeValueAsString(student);
        System.out.println(s);
    }

    @Test
    @DisplayName("序列化：涉及日期时间")
    public void testJsonDemo3() throws JsonProcessingException {
        Student student = new Student("Mike", 12, 130, LocalDateTime.now(), null);
        objectMapper.registerModule(new JavaTimeModule());
        String s = objectMapper.writeValueAsString(student);
        System.out.println(s);
    }

    @Test
    @DisplayName("从 json 获得 JsonNode 对象（树模型）")
    public void testJsonDemo4() throws IOException {
        // 树模型
        String studentJson = "{\"data\":{\"name\":\"Mike\", \"age\":18}}";
        JsonNode rootNode = objectMapper.readTree(studentJson);
        JsonNode jsonNode = rootNode.get("data");
        String string = jsonNode.toString();
        System.out.println(string);
    }

    @Test
    @DisplayName("从 JsonNode 构造 json")
    public void testJsonDemo5() {
        ObjectNode jsonNodes = objectMapper.createObjectNode();
        jsonNodes.put("name", "Mike");
        jsonNodes.put("age", 18);
        String string = jsonNodes.toString();
        System.out.println(string);
    }

    @Test
    @DisplayName("反序列化时，对于嵌套的数据的处理")
    public void testJsonDemo6() throws IOException {
        // {"name":"Mike", "info":"{"age":"hello"}"}
        String studentJson = "{'name':'Mike', 'info':['hello','world']}";
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        // objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        // DeserializationFeature.
        Student student = objectMapper.readValue(studentJson, Student.class);
        System.out.println(student);
        // 反序列化时，对于
    }

    @Test
    public void testJsonDemo7() throws IOException {
        String teacherJson = "{'name':'Mike', 'info':[{'key1':'hello'}, {'key1':'world'}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        Teacher teacher = objectMapper.readValue(teacherJson, Teacher.class);
        System.out.println(teacher);
    }

    @Test
    public void testJsonDemo8() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String teacherJson = "{'name':'Mike', 'age':18}";
        Teacher teacher = objectMapper.readValue(teacherJson, Teacher.class);
        System.out.println(teacher);
    }
}