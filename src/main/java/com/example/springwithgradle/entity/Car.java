package com.example.springwithgradle.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
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
    private String fuelType; // Petrol, Diesel, Electric

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key column in cars table
    private User owner; // The user who owns this car

}
