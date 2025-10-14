package edu.note.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultConsumer;

/**
 * @author jackylee
 * @date 2025-10-14 12:36
 */
public class MyCustomConsumer extends DefaultConsumer {

    private final Endpoint endpoint;

    public MyCustomConsumer(MyCustomEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        // 在这里启动消费逻辑，添加定时器或监听器等
        System.out.println("Starting custom consumer");
    }

    @Override
    protected void doStop() throws Exception {
        // 清理资源，停止消费逻辑
        super.doStop();
        System.out.println("Stopping custom consumer");
    }
    
    public void process(Exchange exchange) throws Exception {
        // 处理消息逻辑, 获取并处理数据
        String message = exchange.getIn().getBody(String.class);
        System.out.println("Processing message: " + message);

        // 使用 processor 进行进一步处理
        getProcessor().process(exchange);
    }

}
