package com.example.springwithgradle.dto.parameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchParams {
    private String make;
    private String model;
    private Integer minHorsepower;
    private Integer maxHorsepower;
    private String ownerName;
    private int page = 0;
    private int size = 10;
}
