package com.example.hotel.demo.controller;

import com.example.hotel.demo.common.PageResult;
import com.example.hotel.demo.entity.RequestDTO;
import com.example.hotel.demo.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    IHotelService hotelService;


    @PostMapping("/list")
    public PageResult getList(@RequestBody RequestDTO data) {
        log.info("/list:接收到数据：{}", data);
        return hotelService.search(data);

    }

    @PostMapping("/filters")
    public Map<String, List<String>> getFilter(@RequestBody RequestDTO data) {
        log.info("/filters:接收到数据：{}", data);
        return hotelService.filters(data);
    }

    @GetMapping("/suggestion")
    public List<String> getSuggestion(@RequestParam String key){
        log.info("/suggestion:接收到的数据为{}", key);
        return hotelService.suggestion(key);
    }

}
