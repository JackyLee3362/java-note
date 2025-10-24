package edu.note.fastjson;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author jackylee
 * @date 2025/7/29 16:57
 */
public class TestFastJson {

    @Test
    void test() throws IOException {
        // 使用ClassLoader读取resource目录下的文件
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test-001.json");
            ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            if (inputStream == null) {
                throw new FileNotFoundException("Resource file 'test-001.json' not found");
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String json = result.toString("UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(json);
            System.out.println(jsonObject);
            System.out.println(jsonObject != null);
            assertInstanceOf(JSONObject.class, jsonObject);
            assertInstanceOf(Map.class, jsonObject);
            assertInstanceOf(JSONObject.class, jsonObject.get("address"));
            assertInstanceOf(Map.class, jsonObject.get("address"));
            assertInstanceOf(JSONArray.class, jsonObject.get("scores"));
            assertInstanceOf(List.class, jsonObject.get("scores"));
        }
    }
}
