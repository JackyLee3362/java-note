package com.example.hotel.demo.common;

import com.example.hotel.demo.entity.HotelDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private long total;
    private List<HotelDoc> hotels;
}
