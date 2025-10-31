package edu.note.spring.mq.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @description: 测试简单队列的发送
     * @author: Jacky Lee
     * @date: 2024/3/22 11:46
     */
    @Test
    void testSendMessage2SimpleQueue() {
        // 如果队列名称不存在，则会被丢弃
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
        System.out.println("消息发送成功");
    }

    /**
     * @description: 测试 Working Queue 工作队列
     * @author: Jacky Lee
     * @date: 2024/3/22 11:46
     */
    @Test
    void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    void testSendFanoutExchange() {
        // 交换机名称
        String exchangeName = "note.fanout";
        // 消息
        String message = "hello, every one!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "note.direct";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", "hello, red");
        rabbitTemplate.convertAndSend(exchangeName, "yellow", "hello, yellow");
    }

    @Test
    void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "note.topic";
        // 消息
        String message = "今天天气不错，我的心情好极了!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }
}
