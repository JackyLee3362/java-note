package com.example.hotel.admin.controller;

import com.example.hotel.admin.constant.MqConstants;
import com.example.hotel.admin.entity.Hotel;
import com.example.hotel.admin.entity.PageResult;
import com.example.hotel.admin.service.IHotelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("hotel")
@Slf4j
public class HotelController {
    static String msg = "MQ消息={交换机为%s, 键为%s}, hotel对象={id=%d,name=%s}";

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 根据 id 查询，不会放入 mq
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    /**
     * 查询分页，不会放入 mq
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public PageResult hotelList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "1") Integer size) {
        Page<Hotel> result = hotelService.page(new Page<>(page, size));

        return new PageResult(result.getTotal(), result.getRecords());
    }

    /**
     * 新增酒店，放入 mq 消息队列，指定交换机，指定 key
     *
     * @param hotel
     */
    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel) {
        hotelService.save(hotel);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE, MqConstants.HOTEL_INSERT_KEY, hotel.getId());
        log.info("新增酒店" + String.format(msg, MqConstants.HOTEL_EXCHANGE, MqConstants.HOTEL_INSERT_KEY, hotel.getId(),
                hotel.getName()));
    }

    /**
     * 更改酒店，放入 mq 消息队列，指定交换机，指定 key
     *
     * @param hotel
     */
    @PutMapping()
    public void updateById(@RequestBody Hotel hotel) {
        if (hotel.getId() == null) {
            throw new InvalidParameterException("id不能为空");
        }
        hotelService.updateById(hotel);
        // 手动抛出一个错误，让更新只在数据库层面
        if (hotel.getId() == 36934) {
            throw new RuntimeException();
        }
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE, MqConstants.HOTEL_INSERT_KEY, hotel.getId());
        log.info("更新酒店" + String.format(msg, MqConstants.HOTEL_EXCHANGE, MqConstants.HOTEL_INSERT_KEY, hotel.getId(),
                hotel.getName()));
    }

    /**
     * 根据 id 删除酒店，放入 mq 消息队列，指定交换机，指定 key
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        hotelService.removeById(id);
        rabbitTemplate.convertAndSend(MqConstants.HOTEL_EXCHANGE, MqConstants.HOTEL_DELETE_KEY, id);
        log.info("删除酒店" + String.format(msg, MqConstants.HOTEL_EXCHANGE, MqConstants.HOTEL_INSERT_KEY, id, "null"));
    }
}
