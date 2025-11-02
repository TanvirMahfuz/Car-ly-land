package com.example.springwithgradle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "car")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maker; // Toyota, Ford
    private String model; // Corolla, Mustang
    private int year; // Manufacturing year
    private String color;
    private double price;
    private int seats; // Number of seats
    private String transmission; // Automatic, Manual
    private Long engineID;
    private Long ownerID;
}
