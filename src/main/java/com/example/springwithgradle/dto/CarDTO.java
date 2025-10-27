package com.example.springwithgradle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CarDTO {
    private Long carId;
    private String make;
    private String model;
    private int year;
    private String ownerName;
    private String engineType;
    private int horsepower;
}
