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
    private Car car;
    private User user;
    private Engine engine;

}
