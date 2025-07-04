import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import spock.lang.Specification

import java.text.SimpleDateFormat

/**
 * @author jackylee
 * @date 2025/7/1 17:36
 */
class Basic02MapperProperty extends Specification {


    def "测试属性"() {
        def mapper = new ObjectMapper()
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
