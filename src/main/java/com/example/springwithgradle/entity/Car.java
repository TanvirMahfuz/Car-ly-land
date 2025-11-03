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

    private String maker;
    private String model;
    private int year;
    private String color;
    private double price;
    private int seats;
    private String transmission;
    private Long engineID;
    private Long ownerID;
}
