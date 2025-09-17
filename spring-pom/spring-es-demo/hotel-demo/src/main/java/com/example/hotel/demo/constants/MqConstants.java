package com.example.hotel.demo.constants;

public class MqConstants {
    // 交换机
    public final static String HOTEL_EXCHANGE = "hotel.topic";
    // 插入队列
    public final static String HOTEL_INSERT_QUEUE = "hotel.insert.queue";
    // 删除队列
    public final static String HOTEL_DELETE_QUEUE = "hotel.delete.queue";
    // 插入Routing Key
    public final static String HOTEL_INSERT_KEY = "hotel.insert";
    // 删除Routing Key
    public final static String HOTEL_DELETE_KEY = "hotel.delete";
}
