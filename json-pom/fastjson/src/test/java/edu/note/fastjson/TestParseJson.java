package edu.note.fastjson;

import com.alibaba.fastjson.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025/8/4 11:25
 */
public class TestParseJson {

    @Test
    void testParse() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test-002.json")) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
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

            // 用户id（B端则为商家会员id）
            Long userId = jsonObject.getLong("userId");
            // 快捷同步的序列号（用于定位排查问题）
            String bizSerialNumber = jsonObject.getString("bindcardSeqId");
            // 消息生产时间（近似于绑卡时间，两者相差不会超过1s）
            Long sendTime = jsonObject.getLong("time");
            // 业务方绑卡入口来源（鲸湾场景为utmSource）
            String utmSource = jsonObject.getString("entry");
            // 获取银行类型
            Integer bankTypeId = jsonObject.getJSONObject("bankInfo").getInteger("bankTypeId");

            Map<String, Object> context = new HashMap<>();
            context.put("utmSource", utmSource);
            context.put("bankType", bankTypeId);
            System.out.println(context);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
