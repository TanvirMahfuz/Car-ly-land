package com.example.springwithgradle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "engines") // Specifies the table name
@Data
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Petrol, Diesel, Electric, Hybrid
    private int horsepower; // Engine power in HP
    private double displacement; // in liters
    private int cylinders; // number of cylinders
    private double torque; // Torque in Nm
    private String fuelSystem; // Direct Injection, Carburetor
    private double fuelEfficiency; // in km/l or mpg
    private boolean turbocharged; // true if turbocharged
}

