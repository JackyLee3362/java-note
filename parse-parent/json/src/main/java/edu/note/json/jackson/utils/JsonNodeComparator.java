package edu.note.jackson.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * Json对象比较工具类
 * 用于比较两个JsonNode对象是否相等，支持复杂嵌套结构和数组比较
 *
 * @author jackylee
 * @date 2025/11/27 16:38
 */
@Slf4j
public class JsonNodeComparator {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 比较两个JsonNode对象是否相等
     *
     * @param node1 第一个JsonNode对象
     * @param node2 第二个JsonNode对象
     * @return 如果相等返回true，否则返回false
     */
    public static boolean isEqual(JsonNode node1, JsonNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }

        // 如果两个对象是同一个引用，直接返回true
        if (node1 == node2) {
            return true;
        }

        // 比较节点类型
        if (node1.getNodeType() != node2.getNodeType()) {
            return false;
        }

        // 根据节点类型进行比较
        switch (node1.getNodeType()) {
            case OBJECT:
                return isEqualObjects(node1, node2);
            case ARRAY:
                return isEqualArrays(node1, node2);
            case STRING:
                return node1.asText().equals(node2.asText());
            case NUMBER:
                return node1.asDouble() == node2.asDouble();
            case BOOLEAN:
                return node1.asBoolean() == node2.asBoolean();
            case NULL:
                return true;
            default:
                log.warn("不支持的Json节点类型: {}", node1.getNodeType());
                return false;
        }
    }

    /**
     * 比较两个ObjectNode是否相等
     *
     * @param node1 第一个ObjectNode
     * @param node2 第二个ObjectNode
     * @return 如果相等返回true，否则返回false
     */
    private static boolean isEqualObjects(JsonNode node1, JsonNode node2) {
        ObjectNode obj1 = (ObjectNode) node1;
        ObjectNode obj2 = (ObjectNode) node2;

        // 比较字段数量
        if (obj1.size() != obj2.size()) {
            return false;
        }

        // 比较每个字段
        Iterator<Map.Entry<String, JsonNode>> fields = obj1.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value1 = field.getValue();
            JsonNode value2 = obj2.get(key);

            if (!isEqual(value1, value2)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 比较两个ArrayNode是否相等
     *
     * @param node1 第一个ArrayNode
     * @param node2 第二个ArrayNode
     * @return 如果相等返回true，否则返回false
     */
    private static boolean isEqualArrays(JsonNode node1, JsonNode node2) {
        ArrayNode arr1 = (ArrayNode) node1;
        ArrayNode arr2 = (ArrayNode) node2;

        // 比较数组长度
        if (arr1.size() != arr2.size()) {
            return false;
        }

        // 逐个比较数组元素
        for (int i = 0; i < arr1.size(); i++) {
            if (!isEqual(arr1.get(i), arr2.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 比较两个Json字符串是否相等
     *
     * @param jsonStr1 第一个Json字符串
     * @param jsonStr2 第二个Json字符串
     * @return 如果相等返回true，否则返回false
     */
    public static boolean isEqual(String jsonStr1, String jsonStr2) {
        try {
            JsonNode node1 = OBJECT_MAPPER.readTree(jsonStr1);
            JsonNode node2 = OBJECT_MAPPER.readTree(jsonStr2);
            return isEqual(node1, node2);
        } catch (Exception e) {
            log.error("比较Json字符串时发生异常: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 比较两个对象是否相等（自动转换为JsonNode）
     *
     * @param obj1 第一个对象
     * @param obj2 第二个对象
     * @return 如果相等返回true，否则返回false
     */
    public static boolean isEqualObjects(Object obj1, Object obj2) {
        try {
            JsonNode node1 = OBJECT_MAPPER.valueToTree(obj1);
            JsonNode node2 = OBJECT_MAPPER.valueToTree(obj2);
            return isEqual(node1, node2);
        } catch (Exception e) {
            log.error("比较对象时发生异常: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 深度比较两个JsonNode对象，返回详细的比较结果
     *
     * @param node1 第一个JsonNode对象
     * @param node2 第二个JsonNode对象
     * @return 比较结果对象
     */
    public static CompareResult deepCompare(JsonNode node1, JsonNode node2) {
        CompareResult result = new CompareResult();
        deepCompareInternal(node1, node2, "", result);
        return result;
    }

    /**
     * 深度比较的内部实现
     */
    private static void deepCompareInternal(JsonNode node1, JsonNode node2, String path, CompareResult result) {
        if (node1 == null && node2 == null) {
            return;
        }

        if (node1 == null || node2 == null) {
            result.addDifference(path, node1, node2);
            return;
        }

        if (node1.getNodeType() != node2.getNodeType()) {
            result.addDifference(path, node1, node2);
            return;
        }

        switch (node1.getNodeType()) {
            case OBJECT:
                compareObjectsDeep((ObjectNode) node1, (ObjectNode) node2, path, result);
                break;
            case ARRAY:
                compareArraysDeep((ArrayNode) node1, (ArrayNode) node2, path, result);
                break;
            case STRING:
            case NUMBER:
            case BOOLEAN:
            case NULL:
                if (!isEqual(node1, node2)) {
                    result.addDifference(path, node1, node2);
                }
                break;
            default:
                result.addDifference(path, node1, node2);
                break;
        }
    }

    /**
     * 深度比较两个对象
     */
    private static void compareObjectsDeep(ObjectNode obj1, ObjectNode obj2, String path, CompareResult result) {
        // 检查obj1中有但obj2中没有的字段
        Iterator<Map.Entry<String, JsonNode>> fields1 = obj1.fields();
        while (fields1.hasNext()) {
            Map.Entry<String, JsonNode> field = fields1.next();
            String key = field.getKey();
            String currentPath = path.isEmpty() ? key : path + "." + key;

            if (!obj2.has(key)) {
                result.addDifference(currentPath, field.getValue(), null);
            } else {
                deepCompareInternal(field.getValue(), obj2.get(key), currentPath, result);
            }
        }

        // 检查obj2中有但obj1中没有的字段
        Iterator<Map.Entry<String, JsonNode>> fields2 = obj2.fields();
        while (fields2.hasNext()) {
            Map.Entry<String, JsonNode> field = fields2.next();
            String key = field.getKey();

            if (!obj1.has(key)) {
                String currentPath = path.isEmpty() ? key : path + "." + key;
                result.addDifference(currentPath, null, field.getValue());
            }
        }
    }

    /**
     * 深度比较两个数组
     */
    private static void compareArraysDeep(ArrayNode arr1, ArrayNode arr2, String path, CompareResult result) {
        if (arr1.size() != arr2.size()) {
            result.addDifference(path + ".length", arr1.size(), arr2.size());
            return;
        }

        for (int i = 0; i < arr1.size(); i++) {
            String currentPath = path + "[" + i + "]";
            deepCompareInternal(arr1.get(i), arr2.get(i), currentPath, result);
        }
    }

    /**
     * 比较结果类
     */
    public static class CompareResult {
        private boolean equal = true;
        private final java.util.List<String> differences = new java.util.ArrayList<>();

        public boolean isEqual() {
            return equal;
        }

        public java.util.List<String> getDifferences() {
            return differences;
        }

        void addDifference(String path, Object value1, Object value2) {
            equal = false;
            String diff = String.format("路径 '%s': %s ≠ %s", path, value1, value2);
            differences.add(diff);
        }

        @Override
        public String toString() {
            if (equal) {
                return "对象相等";
            } else {
                return "对象不相等，差异如下：\n" + String.join("\n", differences);
            }
        }
    }
}
