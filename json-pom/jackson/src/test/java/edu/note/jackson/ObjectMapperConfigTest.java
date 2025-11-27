package edu.note.jackson;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author jackylee
 * @date 2025-11-27 17:26
 */
public class ObjectMapperConfigTest {

    @Test
    @DisplayName("测试配置单引号")
    void testSingleQuote() {
        final ObjectMapper objectMapper = new ObjectMapper();
        // 等价
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    @Test
    @DisplayName("测试配置单引号")
    void testSingleValueAsArray() {
        final ObjectMapper objectMapper = new ObjectMapper();
        // 等价
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    @Test
    @DisplayName("测试")
    void test01() {

        ObjectMapper mapper = new ObjectMapper();
        // ---------- 一般设置 ----------
        mapper.disable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES); // 允许没有引号的字段名（非标准）
        // ---------- 序列化属性设置 ----------
        // 优化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 序列化空的 POPJ
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 不将date转化成timestamp
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        // ---------- 反序列化属性设置 ----------
        // 忽略未知属性 避免反序列化时java中缺少对应属性出现错误；
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 不将空转化为null
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // SimpleDateFormat 内部使用是线程安全的
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

}
