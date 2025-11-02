package com.example.springwithgradle.dto.parameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchParams {

    private String maker;          // e.g. Toyota, Ford
    private String model;          // e.g. Corolla, Mustang
    private int year;              // Manufacturing year
    private String color;          // e.g. Red, Black
    private double price;          // e.g. 25000.00
    private int seats;             // e.g. 5
    private String transmission;   // e.g. Automatic, Manual
    private Long engineID;         // Reference to engine
    private Long ownerID;          // Reference to owner
}

