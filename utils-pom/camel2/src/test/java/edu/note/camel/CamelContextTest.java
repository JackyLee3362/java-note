package edu.note.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.ModelCamelContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2025-10-13 20:32
 */
public class CamelContextTest {

    @Test
    @DisplayName("测试")
    void test01() throws Exception {
        ModelCamelContext context = new DefaultCamelContext();
        context.start();
        context.addRoutes(new FooRouter());

        // 只是为了保证线程不退出
        synchronized (CamelContextTest.class) {
            CamelContextTest.class.wait();
        }

    }

    static class FooRouter extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("jetty:http://0.0.0.0:8282/doHelloWorld")
                    .process(new HttpProcessor())
                    .to("log:helloworld?showExchangeId=true");
        }
    }

    static class HttpProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            // 由于消息明确是 http, 所以使用该类，否则建议 org.apache.camel.Message
            // HttpMessage in = (HttpMessage) exchange.getIn();
            Message in = exchange.getIn();
            InputStream bodyStream = (InputStream) in.getBody();
            String inputContext = handleStream(bodyStream);
            bodyStream.close();

            if (exchange.getPattern() == ExchangePattern.InOut) {
                Message outMessage = exchange.getOut();
                outMessage.setBody(inputContext + "|| out");
            }
        }

        private String handleStream(InputStream stream) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] contextBytes = new byte[4096];
            int realLen;
            while ((realLen = stream.read(contextBytes, 0, 4096)) != -1) {
                out.write(contextBytes, 0, realLen);
            }
            // 返回从 Stream 读取的字符串
            try {
                return new String(out.toByteArray(), "UTF-8");
            } finally {
                out.close();
            }
        }

    }

}
