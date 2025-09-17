package com.example.hotel.admin.mapper;

import com.example.hotel.admin.entity.Hotel;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface HotelMapper extends BaseMapper<Hotel> {
}
