package edu.note.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author jackylee
 * @date 2025-10-14 12:31
 */
public class MyProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // 获取并处理消息
        String originalBody = exchange.getIn().getBody(String.class);
        String modifiedBody = originalBody.toUpperCase();

        // 将处理后的消息设置回 Exchange
        exchange.getIn().setBody(modifiedBody);

        // 打印或记录日志
        System.out.println("Processed message: " + modifiedBody);
    }
}
