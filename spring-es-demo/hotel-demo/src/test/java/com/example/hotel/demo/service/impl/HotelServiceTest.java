package com.example.hotel.demo.service.impl;

import com.example.hotel.demo.entity.RequestDTO;
import com.example.hotel.demo.service.IHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class HotelServiceTest {

    @Autowired
    IHotelService hotelService;

    @Test
    void filters() {
        RequestDTO data = new RequestDTO();
        Map<String, List<String>> filters = hotelService.filters(data);
        System.out.println(filters);
    }
}