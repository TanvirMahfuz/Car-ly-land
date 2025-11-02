package com.example.springwithgradle.dto;

import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String color;
    private Long carId;
    private String maker;
    private String model;
    private int year;
    private User owner;
    private Engine engine;

    public CarDTO(Car car, User user, Engine engine) {
        if (car != null) {
            this.color = car.getColor();
            this.carId = car.getId();
            this.maker = car.getMaker();
            this.model = car.getModel();
            this.year = car.getYear();
            this.owner = user;
            this.engine =  engine;
        }
    }
}
