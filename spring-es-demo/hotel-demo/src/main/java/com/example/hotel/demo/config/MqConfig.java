package com.example.hotel.demo.config;

import com.example.hotel.demo.constants.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    @Bean
    public Exchange topicExchange(){
        return new TopicExchange(MqConstants.HOTEL_EXCHANGE, true, false);
    }
    @Bean
    public Queue hotelInsertQueue(){
        return new Queue(MqConstants.HOTEL_INSERT_QUEUE, true);
    }

    @Bean
    public Queue hotelDeleteQueue(){
        return new Queue(MqConstants.HOTEL_DELETE_QUEUE, true);
    }

    @Bean
    public Binding insertQueueBinding(){
        return BindingBuilder
                .bind(hotelInsertQueue())
                .to(topicExchange())
                .with(MqConstants.HOTEL_INSERT_KEY)
                .noargs();
    }

    @Bean
    public Binding deleteQueueBinding(){
        return BindingBuilder
                .bind(hotelDeleteQueue())
                .to(topicExchange())
                .with(MqConstants.HOTEL_DELETE_KEY)
                .noargs();
    }
}
