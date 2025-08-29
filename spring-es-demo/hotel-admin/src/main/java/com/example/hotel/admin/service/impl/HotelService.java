package com.example.hotel.admin.service.impl;

import com.example.hotel.admin.mapper.HotelMapper;
import com.example.hotel.admin.entity.Hotel;
import com.example.hotel.admin.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
