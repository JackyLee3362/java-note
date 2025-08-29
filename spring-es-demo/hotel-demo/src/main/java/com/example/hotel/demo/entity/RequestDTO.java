package com.example.hotel.demo.entity;

import lombok.Data;

@Data
public class RequestDTO {
    String key;
    Integer page;
    Integer size;
    String sortBy;
    String brand;
    String city;
    String starName;
    Integer minPrice;
    Integer maxPrice;
    String location;
}
