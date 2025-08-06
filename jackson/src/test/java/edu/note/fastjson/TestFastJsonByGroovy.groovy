package edu.note.fastjson

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.sankuai.meituan.ccp.marketing.platform.domain.model.MarketingEvent
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author jackylee
 * @date 2025/8/4 11:45
 */
class TestFastJsonByGroovy extends Specification {

    @Unroll
    def "test 测试 apply"() {
        when:
        def res = (JSONObject) apply(filename as String)
        def set = res.getJSONObject("context").get("bankTypeIdList")
        def res2 = (JSONObject) apply_other(filename as String)
        def set2 = res2.getJSONObject("context").get("bankTypeIdList")

        then:
        set == expectedSet
        set2 == expectedSet2


        where:
        filename              | expectedSet | expectedSet2
        "test-mapper-01.json" | "[39,391]" | "[39,391]"
        "test-mapper-02.json" | "[]"        | "[]"
        "test-mapper-03.json" | "[]"        | null
        "test-mapper-04.json" | "[39,391]" | "[39,391]"


    }

    def apply_other(String source) {
        def json = getClass().classLoader.getResourceAsStream(source)?.text
        if (!json) throw new FileNotFoundException("Resource file 'test-mapper-01.json' not found")

        // 使用Groovy的简洁语法解析JSON
        def jsonObject = JSONObject.parseObject(json)
        Long userId = jsonObject.getLong("userId");
        String bizSerialNumber = jsonObject.getString("bindcardSeqId");
        Long sendTime = jsonObject.getLong("time");
        String utmSource = jsonObject.getString("entry");
        String bankcardInfo = jsonObject.getString("bankcardInfo");

        MarketingEvent event = new MarketingEvent();
        event.setUserId(userId);
        event.setUserType(1);
        event.setType(1000952);
        event.setProductId("000001");
        event.setIsEmitted(0);
        event.setSerialNum(bizSerialNumber);
        event.setTimestamp(sendTime);
        event.setUtmSource(utmSource);

        Map<String, String> context = new HashMap<>(8);
        // 银行卡种列表
        context.put("bankTypeIdList", getBankTypeIdList(bankcardInfo));
        event.setContext(context);

        return JSON.toJSON(event)
    }

    private String getBankTypeIdList(String bankcardInfo) {
        if (Objects.isNull(bankcardInfo)) {
            return null;
        }
        JSONArray jsonArray = JSON.parseArray(bankcardInfo);
        Set<Integer> bankTypeIdList = new HashSet<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer bankTypeId = jsonObject.getInteger("bankTypeId");
            bankTypeIdList.add(bankTypeId);
        }
        return JSON.toJSONString(bankTypeIdList);
    }

    def apply(String source) {
        try {
            // 使用Groovy的资源加载和文本处理能力
            def json = getClass().classLoader.getResourceAsStream(source)?.text
            if (!json) throw new FileNotFoundException("Resource file " + source + "not found")

            // 使用Groovy的简洁语法解析JSON
            def jsonObject = JSONObject.parseObject(json)

            // >>>>>>>>>>>>>>>>>>>>>>>>>>> 复制到代码
            // 用户id（B端则为商家会员id）
            def userId = jsonObject?.getLong("userId");
            // 快捷同步的序列号（用于定位排查问题）
            def bizSerialNumber = jsonObject?.getString("bindcardSeqId");
            // 消息生产时间（近似于绑卡时间，两者相差不会超过1s）
            def sendTime = jsonObject?.getLong("time");
            // 业务方绑卡入口来源（鲸湾场景为utmSource）
            def utmSource = jsonObject?.getString("entry");
            // 获取银行类型列表，并转换为Set类型
            def bankTypeList = jsonObject?.getJSONArray("bankcardInfo")?.findResults {
                def bankInfo = (JSONObject) it
                bankInfo.containsKey("bankTypeId") ? bankInfo.getInteger("bankTypeId") : null
            }?.toSet() ?: [] as Set;
            // 构建上下文
            def context = [utmSource: utmSource, bankTypeIdList: JSON.toJSONString(bankTypeList)];
            def marketingEvent = new MarketingEvent(
                    serialNum: bizSerialNumber,
                    // 只有美团用户
                    userType: 1,
                    userId: userId,
                    // 【必填】与【事件源】中的【产品业务线】保持一致; 支付 "000001"
                    productId: "000001",
                    // 【鲸湾配置中心】的【事件源】
                    type: 1000992,
                    isEmitted: 0,
                    timestamp: sendTime,
                    utmSource: utmSource,
                    context: context
            );
            // <<<<<<<<<<<<<<<<<<<<<<<<<
            def res = JSONObject.toJSON(marketingEvent)

            // println res
            // println marketingEvent
            // println context
            // println context.get("bankTypeIdList").class
            // println "bankTypeList类型: ${bankTypeList.getClass()}"
            return res
        } catch (Exception e) {
            e.printStackTrace()
        }
    }


    def "test"() {
        expect:
        JSON.toJSONString(null) == "null"
    }
}
